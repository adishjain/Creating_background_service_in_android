package last_star.pronerd.com.myapplication;

/**
 * Created by Adish Jain on 06-01-2016.
 */
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class HelloService extends Service {
    public static int i;

    private static final String TAG = "HelloService";

    private boolean isRunning  = false;

    @Override
    public void onCreate() {
        Log.i(TAG, "Service onCreate");

        isRunning = true;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i(TAG, "Service onStartCommand");
        for(int j=1;j<5;j++)
        Toast.makeText(getApplicationContext(),"This is THE START:"+j,Toast.LENGTH_SHORT).show();

        //Creating new thread for my service
        //Always write your long running tasks in a separate thread, to avoid ANR
        new Thread(new Runnable() {
            @Override
            public void run() {



                for (i = 0; i < 5; i++) {

                    try {
                        Thread.sleep(5000);

                    } catch (Exception e) {
                    }

                    if(isRunning){
                        Log.i(TAG, "Service running");
                    }
                }


                //stopSelf();
            }
        }).start();

        return Service.START_STICKY;
    }


    @Override
    public IBinder onBind(Intent arg0) {
        Log.i(TAG, "Service onBind");
        return null;
    }

    @Override
    public void onDestroy() {

        isRunning = false;
        Toast.makeText(getApplicationContext(),"This is :"+i,Toast.LENGTH_SHORT).show();
        Log.i(TAG, "Service onDestroy");
    }


}