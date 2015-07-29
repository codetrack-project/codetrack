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
 * FileDatabase based Repository implementation
 *
 * @author josecmoj at 07/07/15.
 */
@Component
@Scope("prototype")
public class FileRepository<T> implements Repository<T>{

    /**
     * File database instance
     */
    protected FileDatabase database;

    /**
     * Project reference instance
     */
    protected FileProject project;

    /**
     * Default constructor
     */
    public FileRepository(){
        super();
    }

    /**
     * Construtor with parameters
     *
     * @param database - database instance reference
     * @param project  - project instance reference
     */
    public FileRepository(FileDatabase database, FileProject project){
        super();
        setDatabase(database);
        setProject(project);
    }

    /**
     * Access project instance
     *
     * @return instance of Project
     */
    public FileProject getProject() {
        return project;
    }

    /**
     * Setting project instance
     *
     * @param project - the project instance
     */
    public void setProject(FileProject project) {
        this.project = project;
    }

    /**
     * Access database instance
     * @return FileDatabase instance
     */
    public FileDatabase getDatabase() {
        return database;
    }

    /**
     * Setting database instance
     * @param database - FileDatabase instance
     */
    public void setDatabase(FileDatabase database) {
        this.database = database;
    }

    /**
     * Tool method to get item class
     * @param item - generic item
     * @return Class of item
     */
    private Class getItemClass(T item){
        return item.getClass();
    }

    /**
     * {@inheritDoc}
     * Put item data in the project graph and change the database state
     *
     * @param item instance to be saved
     * @return item instance with saved state information
     * @throws CanNotSaveData when item is null
     */
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

    /**
     * {@inheritDoc}
     *
     * Remove item data from the project graph and change database state
     * @param item instance to be removed
     * @return item removed instance
     * @throws CanNotRemoveData when item is null or item not found in project data graph
     */
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

    /**
     * Search item in the project data graph
     * @param request Search request
     * @return SearchResponse instance
     * @throws CanNotFoundData if request is null or the item is not found in the project data graph
     */
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

        throw new CanNotFoundData("Can not found null item!");

    }

    /**
     * Search the first item in the project graph by id
     * @param request - SearchOneById instance
     * @return SearchResponse with item
     * @throws CanNotFoundData when no item found int project graph
     */
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

    /**
     * Search the items in the project graph by id
     * @param request - SearchLikeId instance
     * @return SearchResponse with items
     * @throws CanNotFoundData when no item found int project graph
     */
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
