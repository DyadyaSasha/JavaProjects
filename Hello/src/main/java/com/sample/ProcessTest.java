package com.sample;

import java.util.Map;
import java.util.HashMap;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * This is a sample file to launch a process.
 */
public class ProcessTest {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
	        KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
        	KieSession kSession = kContainer.newKieSession("ksession-process");
			
        	//указываем входные параметры для диаграммы BPMN
        	Map<String, Object> params = new HashMap<>();
        	params.put("income", 1200);
        	
            // start a new process instance
            kSession.startProcess("com.sample.bpmn.hello", params);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
