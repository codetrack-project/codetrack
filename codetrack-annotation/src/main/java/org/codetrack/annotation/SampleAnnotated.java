package org.codetrack.annotation;

/**
 * Created by josecmoj on 14/04/15.
 */
@Product(id = "RELEASE 02-70", cases = {
        @Case(id = "UC1978", revisions = {})})
@Artifacts(artifacts = {"application.properties", "methodRule.xhtml"})
public class SampleAnnotated {


    @Rule(id = "RN12345", caseid = "UC1978")
    @Artifacts(artifacts = {"application.properties", "methodRule.xhtml"})
    public void methodRule(){


    }

    @Rule(id = "RN23452", caseid = "UC1989")
    @Fix(id = "4567", type = FixType.EXP)
    public void methodRuleAndFix(){

    }

    @Rules(rule = {
            @Rule(id = "RN23434", caseid = "UC1978"),
            @Rule(id = "RN23431", caseid = "UC1989")
    })
    public void methodMultiRules(){

    }

    @Fixes(fixes = {
            @Fix(id = "2882", type = FixType.EXP)
    })
    public void methodMultiFixes(){


    }


}
