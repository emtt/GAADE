package gaade.mobilize.com.aaade;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class JobEschedulerActivity extends AppCompatActivity {

    Button btnJob;
    public static final int JOB_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_escheduler);

        btnJob = (Button) findViewById(R.id.btnJob);

        btnJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dotheJob();
            }
        });

    }

    @TargetApi(23)
    public void dotheJob(){
        ComponentName serviceName = new ComponentName(this, MyJobService.class);
        JobInfo jobInfo = new JobInfo.Builder(JOB_ID, serviceName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_UNMETERED)
                .setRequiresDeviceIdle(true)
                .setRequiresCharging(true)
                .setOverrideDeadline(400) // Remove comment for faster testing.
                .build();

        JobScheduler scheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
        int result = scheduler.schedule(jobInfo);
        if (result == JobScheduler.RESULT_SUCCESS) {
            Toast.makeText(this, "JobSchedule exitoso", Toast.LENGTH_LONG).show();
        }

    }
}
