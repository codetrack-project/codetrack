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

package org.codetrack.annotation.sample;

import org.codetrack.annotation.behavior.Rule;
import org.codetrack.annotation.behavior.Rules;
import org.codetrack.annotation.definition.*;
import org.codetrack.annotation.fixing.Fix;
import org.codetrack.annotation.fixing.Fixes;
import org.codetrack.annotation.identify.Product;
import org.codetrack.annotation.identify.Reference;
import org.codetrack.annotation.temporal.Iteration;

/**
 * Sample annotate class
 *
 * Created by josecmoj on 14/04/15.
 */
@Product(id = "RELEASE 02-70",
        usecases = @UseCases({
                @UseCase(id = "UC1978", revisions = {})}))
@Reference(id = "CONFIGFILE", value = "application.properties")
@Requirements({
        @Requirement(id = "RQ2032", story = @Story(id = "ST0123")),
        @Requirement(id = "RQ345")
})
@Iteration(id = "IT012")
public class SampleAnnotated {

    @Rule(id = "RN12345", usecase = @UseCase(id = "UC1978"))
    @Reference(id = "CONFIGFILE")
    public void methodRule(){
        System.out.println("methodRule");
    }

    @Rule(id = "RN23452", usecase = @UseCase(id = "UC1989"))
    @Fix(id = "4567", type = "EXP", observation = "This fixing is necessary...")
    public void methodRuleAndFix(){
        System.out.println("methodRuleAndFix");
    }

    @Rules({
            @Rule(id = "RN23434", usecase = @UseCase(id = "UC1978")),
            @Rule(id = "RN23431", usecase = @UseCase(id = "UC1989"))
    })
    public void methodMultiRules(){
        System.out.println("methodMultiRules");
    }

    @Fixes({
            @Fix(id = "2882", type = "SLA", iteration = @Iteration(id = "IT012"))
    })
    public void methodMultiFixes(){
        System.out.println("methodMultiFixes");
    }

    @Iteration(id = "IT012")
    @Reference(id = "ESPEC234", values = {"INTEGRATION SYS.DOC", "PAGE 10"})
    @Feature(id = "FEAT-089", description = "Demonstrate the Feature tag", story = @Story(id = "ST0123"))
    public void methodFeature() {
        System.out.println("methodFeature");
    }



}
