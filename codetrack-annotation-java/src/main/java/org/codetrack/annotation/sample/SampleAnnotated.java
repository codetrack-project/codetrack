/*
 * Copyright 2015 the original author or authors members of codetrack.org
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.codetrack.annotation.sample;

import org.codetrack.annotation.*;

/**
 * Sample annotate class
 *
 * Created by josecmoj on 14/04/15.
 */
@Product(value = "RELEASE 02-70", usecases = {
        @UseCase(value = "UC1978", revisions = {})})
@Artifacts(artifacts = {"application.properties", "methodRule.xhtml"})
@Requirements({
        @Requirement(value = "RQ2032", story = @Story("ST0123")),
        @Requirement("RQ345")
})
public class SampleAnnotated {

    @Rule(value = "RN12345", usecase = @UseCase("UC1978"))
    @Artifacts(artifacts = {"application.properties"})
    public void methodRule(){
        System.out.println("methodRule");
    }

    @Rule(value = "RN23452", usecase = @UseCase("UC1989"))
    @Fix(id = "4567", type = FixType.EXP, observation = "This fix is necessary...")
    public void methodRuleAndFix(){
        System.out.println("methodRuleAndFix");
    }

    @Rules({
            @Rule(value = "RN23434", usecase = @UseCase("UC1978")),
            @Rule(value = "RN23431", usecase = @UseCase("UC1989"))
    })
    public void methodMultiRules(){
        System.out.println("methodMultiRules");
    }

    @Fixes({
            @Fix(id = "2882", type = FixType.SLA)
    })
    public void methodMultiFixes(){
        System.out.println("methodMultiFixes");
    }


    @Reference(id = "ESPEC234", values = {"INTEGRATION SYS.DOC", "PAGE 10"})
    @Feature(value = "FEAT-089", description = "Demonstrate the Feature tag", story = @Story("ST0123"))
    public void methodFeature() {
        System.out.println("methodFeature");
    }



}
