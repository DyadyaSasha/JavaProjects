$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("annotation.feature");
formatter.feature({
  "line": 1,
  "name": "annotation",
  "description": "",
  "id": "annotation",
  "keyword": "Feature"
});
formatter.before({
  "duration": 5548048821,
  "status": "passed"
});
formatter.background({
  "comments": [
    {
      "line": 2,
      "value": "#This is how background can be used to eliminate duplicate steps"
    }
  ],
  "line": 4,
  "name": "",
  "description": "User navigates to Facebook Given",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 6,
  "name": "I am on Facebook login page",
  "keyword": "Given "
});
formatter.match({
  "location": "Annotation.goToFacebook()"
});
formatter.result({
  "duration": 2060779077,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 8,
      "value": "#Scenario with AND"
    }
  ],
  "line": 9,
  "name": "",
  "description": "",
  "id": "annotation;",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 10,
  "name": "I enter username as \"TOM\"",
  "keyword": "When "
});
formatter.step({
  "line": 11,
  "name": "I enter password as \"JERRY\"",
  "keyword": "And "
});
formatter.step({
  "line": 12,
  "name": "Login should fail",
  "keyword": "Then "
});
formatter.match({
  "arguments": [
    {
      "val": "TOM",
      "offset": 21
    }
  ],
  "location": "Annotation.enterUsername(String)"
});
formatter.result({
  "duration": 5116979282,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "JERRY",
      "offset": 21
    }
  ],
  "location": "Annotation.enterPassword(String)"
});
formatter.result({
  "duration": 11171618872,
  "status": "passed"
});
formatter.match({
  "location": "Annotation.checkFail()"
});
formatter.result({
  "duration": 9848616,
  "status": "passed"
});
formatter.after({
  "duration": 2322194051,
  "status": "passed"
});
formatter.before({
  "duration": 3036749128,
  "status": "passed"
});
formatter.background({
  "comments": [
    {
      "line": 2,
      "value": "#This is how background can be used to eliminate duplicate steps"
    }
  ],
  "line": 4,
  "name": "",
  "description": "User navigates to Facebook Given",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 6,
  "name": "I am on Facebook login page",
  "keyword": "Given "
});
formatter.match({
  "location": "Annotation.goToFacebook()"
});
formatter.result({
  "duration": 1663972103,
  "status": "passed"
});
formatter.scenario({
  "comments": [
    {
      "line": 14,
      "value": "#Scenario with BUT"
    }
  ],
  "line": 15,
  "name": "",
  "description": "",
  "id": "annotation;",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "line": 16,
  "name": "I enter username as \"TOM\"",
  "keyword": "When "
});
formatter.step({
  "line": 17,
  "name": "I enter password as \"JERRY\"",
  "keyword": "And "
});
formatter.step({
  "line": 18,
  "name": "Login should fail",
  "keyword": "Then "
});
formatter.step({
  "line": 19,
  "name": "Relogin option should be available",
  "keyword": "But "
});
formatter.match({
  "arguments": [
    {
      "val": "TOM",
      "offset": 21
    }
  ],
  "location": "Annotation.enterUsername(String)"
});
formatter.result({
  "duration": 5121020308,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "JERRY",
      "offset": 21
    }
  ],
  "location": "Annotation.enterPassword(String)"
});
formatter.result({
  "duration": 11257183180,
  "status": "passed"
});
formatter.match({
  "location": "Annotation.checkFail()"
});
formatter.result({
  "duration": 10411898,
  "status": "passed"
});
formatter.match({
  "location": "Annotation.checkRelogin()"
});
formatter.result({
  "duration": 7627077,
  "status": "passed"
});
formatter.after({
  "duration": 2090864821,
  "status": "passed"
});
});