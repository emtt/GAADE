package gaade.mobilize.com.aaade;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

public class PlayService extends Service {
    MediaPlayer mPlayer;

    @Override
    public void onCreate()
    {
        Toast.makeText(this, "Servicio creado",
                Toast.LENGTH_SHORT).show();
        mPlayer = MediaPlayer.create(this, R.raw.bell_ringing);
        mPlayer.setLooping(false);
    }

    @Override
    public void onStart(Intent intent, int startid)
    {
        Toast.makeText(this, "Servicio iniciado",
                Toast.LENGTH_SHORT).show();
        mPlayer.start();
    }

    @Override
    public void onDestroy()
    {
        Toast.makeText(this, "Servicio destruido",
                Toast.LENGTH_SHORT).show();
        mPlayer.stop();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
