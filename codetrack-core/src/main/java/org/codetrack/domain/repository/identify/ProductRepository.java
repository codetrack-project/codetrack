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

package org.codetrack.domain.repository.identify;

import org.codetrack.annotation.definition.Feature;
import org.codetrack.domain.data.identify.Product;
import org.codetrack.domain.exception.CanNotFoundData;
import org.codetrack.domain.exception.CanNotRemoveData;
import org.codetrack.domain.exception.CanNotSaveData;

import java.util.List;

/**
 * This repository interface defines access to Product data
 *
 * @author josecmoj at 04/07/15.
 * @see Product
 */
@org.codetrack.annotation.identify.Product(id = "codetrack-core")
@Feature(id = "#4-DATABASE")
public interface ProductRepository {

    /**
     * Save the Product instance
     *
     * @param product instance to be saved
     * @return the Product instance saved
     * @throws CanNotSaveData if is not possible save Product instance
     */
    Product save(Product product) throws CanNotSaveData;

    /**
     * Remove an Product instance from graph
     *
     * @param product instance to be removed
     * @return the removed Product instance
     * @throws CanNotRemoveData if is not possible to remove Product instance
     */
    Product remove(Product product) throws CanNotRemoveData;

    /**
     * Find an Product by Id
     *
     * @param id to be find
     * @return the founded Product instance
     * @throws CanNotFoundData if not found an Product instance
     */
    Product findById(String id) throws CanNotFoundData;

    /**
     * Find an Product by Name (exact)
     *
     * @param name to be find
     * @return the founded Product instance
     * @throws CanNotFoundData if not found an Product instance
     */
    Product findByName(String name) throws CanNotFoundData;

    /**
     * Get all Product instances
     *
     * @return list of Products
     */
    List<Product> allProducts() throws CanNotFoundData;

}