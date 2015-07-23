/*
 *  Copyright 2015 the original author or authors members of codetrack.org
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 */

package org.codetrack.database.connection.file;

import com.google.common.base.Strings;
import org.codetrack.domain.data.ProjectItem;
import org.codetrack.exception.CanNotFoundData;
import org.codetrack.exception.CanNotRemoveData;
import org.codetrack.exception.CanNotSaveData;
import org.codetrack.repository.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @author josecmoj at 07/07/15.
 */
@Component
@Scope("prototype")
public class FileRepository<T> implements Repository<T>{

    public FileProject getProject() {
        return project;
    }

    public void setProject(FileProject project) {
        this.project = project;
    }

    protected FileProject project;

    public FileRepository(){
        super();
    }

    public FileRepository(FileDatabase database, FileProject project){
        super();
        setDatabase(database);
        setProject(project);
    }

    public FileDatabase getDatabase() {
        return database;
    }

    public void setDatabase(FileDatabase database) {
        this.database = database;
    }

    protected FileDatabase database;

    private Class getItemClass(T item){
        return item.getClass();
    }

    @Override
    public T save(T item) throws CanNotSaveData {

        if (item != null) {

            if (!project.containsItem(getItemClass(item), item)) {
                if (item instanceof ProjectItem) {
                    ((ProjectItem)item).setProject(this.project);
                    ((ProjectItem)item).setCreatedAt(new Date());
                    ((ProjectItem)item).setUpdatedAt(new Date());
                    project.add(((ProjectItem)item));
                }

            } else {
                if (item instanceof ProjectItem)
                    ((ProjectItem)item).setUpdatedAt(new Date());
            }

            this.database.changed();

            return item;

        } else {
            throw new CanNotSaveData("Can not save null data!");
        }
    }

    @Override
    public T remove(T item) throws CanNotRemoveData {

        if (item != null) {

            if (project.containsItem(getItemClass(item), item)) {

                if (item instanceof ProjectItem) {
                    this.project.remove((ProjectItem) item);
                    ((ProjectItem) item).setProject(null);
                    this.database.changed();
                }

                return item;

            } else {

                throw new CanNotRemoveData("Can not remove data. Its not registered in the project");
            }

        } else {
            throw new CanNotRemoveData("Can not remove null data!");
        }
    }

    @Override
    public SearchResponse<T> search(SearchRequest<T> request) throws CanNotFoundData {

        if (request != null){

            if (request instanceof SearchOneById){
                return searchOneById(((SearchOneById)request));
            }

            if (request instanceof SearchLikeId){
                return searchLikeId(((SearchLikeId)request));
            }
        }

        return null;
    }

    private SearchResponse<T> searchOneById(SearchOneById<T> request) {

        //Validate
        if (Strings.isNullOrEmpty(request.getId())){
            throw new CanNotFoundData("Parameter id not found or is null");
        }

        Map map = project.getMapItems(request.getItemClazz());

        Object item = map.get(request.getId());

        return SearchResponse.newBuilder()
                .request(request)
                .item(item)
                .build();
    }

    private SearchResponse<T> searchLikeId(SearchLikeId<T> request) {

        //Validate
        if (!Strings.isNullOrEmpty(request.getId())){
            throw new CanNotFoundData("Parameter id not found or is null");
        }

        Map map = project.getMapItems(request.getItemClazz());

        SearchResponse.Builder response = SearchResponse.newBuilder();
        response.request(request);

        for(Object key: map.keySet()){

            if (((String)key).startsWith(request.getId())){
                response.addItem(map.get(key));
            }
        }

        return response.build();
    }
}
