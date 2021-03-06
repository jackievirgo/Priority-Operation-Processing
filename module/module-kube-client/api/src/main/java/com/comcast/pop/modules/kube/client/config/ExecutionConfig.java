package com.comcast.pop.modules.kube.client.config;

import com.comcast.pop.modules.kube.client.CpuRequestModulator;
import com.comcast.pop.modules.kube.client.LogLineAccumulator;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *  Details for Pod creation
 */
public class ExecutionConfig
{
    // labels for the pod
    private String externalId = UUID.randomUUID().toString();
    private String externalGroupId = UUID.randomUUID().toString();   // todo where is this used

    private String name;

    private Map<String, String> envVars = new HashMap<>();

    // these are values on the PodConfig that can be overriden
    private String memoryRequestCount;

    // builds a String adhering to the requirements of fabric8 client request for cpu
    private CpuRequestModulator cpuRequestModulator;        // todo should this be on the PodConfig?

    private LogLineAccumulator logLineAccumulator;

    private String logback;

    public ExecutionConfig()
    {
        this("");
    }

    // if we ever need anything else from the podConfig, just make a factory and get rid of this
    public ExecutionConfig(String namePrefix)
    {
        this.name = namePrefix + UUID.randomUUID().toString();
    }

    public String getExternalId()
    {
        return externalId;
    }

    public ExecutionConfig setExternalId(String externalId)
    {
        this.externalId = externalId;
        return this;
    }

    public String getExternalGroupId()
    {
        return externalGroupId;
    }

    public ExecutionConfig setExternalGroupId(String externalGroupId)
    {
        this.externalGroupId = externalGroupId;
        return this;
    }

    public String getName()
    {
        return name;
    }

    public ExecutionConfig setName(String name)
    {
        this.name = name;
        return this;
    }

    public Map<String, String> getEnvVars()
    {
        return envVars;
    }

    public ExecutionConfig setEnvVars(Map<String, String> envVars)
    {
        this.envVars = envVars;
        return this;
    }

    public ExecutionConfig addEnvVar(String name, String value)
    {
        envVars.put(name, value);
        return this;
    }

    public boolean hasEnvVars()
    {
        return envVars != null && !envVars.isEmpty();
    }

    public String getMemoryRequestCount()
    {
        return memoryRequestCount;
    }

    public ExecutionConfig setMemoryRequestCount(String memoryRequestCount)
    {
        this.memoryRequestCount = memoryRequestCount;
        return this;
    }

    public CpuRequestModulator getCpuRequestModulator()
    {
        return cpuRequestModulator;
    }

    public ExecutionConfig setCpuRequestModulator(CpuRequestModulator cpuRequestModulator)
    {
        this.cpuRequestModulator = cpuRequestModulator;
        return this;
    }

    public LogLineAccumulator getLogLineAccumulator()
    {
        return logLineAccumulator;
    }

    public ExecutionConfig setLogLineAccumulator(LogLineAccumulator logLineAccumulator)
    {
        this.logLineAccumulator = logLineAccumulator;
        return this;
    }

    public String getLogback()
    {
        return logback;
    }

    public ExecutionConfig setLogback(String logback)
    {
        this.logback = logback;
        return this;
    }

    // todo getter for the payload in an implementation of this config: HandlerExecutionConfig?


}
