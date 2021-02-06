package net.hyperj.gist.flinkclient;

import com.nextbreakpoint.flinkclient.api.FlinkApi;
import com.nextbreakpoint.flinkclient.model.JobDetailsInfo;
import com.nextbreakpoint.flinkclient.model.JobDetailsInfoJobVertexDetailsInfo;
import com.nextbreakpoint.flinkclient.model.JobIdsWithStatusOverview;

public class FlinkApiClient {

    public static void main(String[] args) throws Exception {
        FlinkApi api = new FlinkApi();
        api.getApiClient().setBasePath(args[0]);
        JobIdsWithStatusOverview jobs = api.getJobs();
        String id = jobs.getJobs().get(0).getId();
        JobDetailsInfo jobDetails = api.getJobDetails(id);
        JobDetailsInfoJobVertexDetailsInfo vertexDetails = jobDetails.getVertices().get(0);
        System.out.println(jobDetails.toString());
        System.out.println(api.getJobTaskDetails(id, vertexDetails.getId()));
        System.out.println(api.getJobPlan(id).toString());
        System.out.println(api.getJobExceptions(id).toString());
        System.out.println(api.getTaskManagersOverview().toString());
        System.out.println(api.getTaskManagerDetails(api.getTaskManagersOverview().getTaskmanagers().get(0).getId()).toString());
        System.out.println(api.getTaskManagerMetrics(api.getTaskManagersOverview().getTaskmanagers().get(0).getId(), null).toString());
        System.out.println(api.getTaskManagerMetrics(api.getTaskManagersOverview().getTaskmanagers().get(0).getId(), "Status.JVM.Memory.Heap.Max").toString());
        System.out.println(api.getJobTaskDetails(id, vertexDetails.getId()).toString());
    }
}
