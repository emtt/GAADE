package gaade.mobilize.com.aaade;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.widget.Toast;

/**
 * Created by user01 on 11/05/17.
 */

public class AlarmReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent){
        // TODO Auto-generated method stub


        // here you can start an activity or service depending on your need
        // for ex you can start an activity to vibrate phone or to ring the phone

        String phoneNumberReciver="5585981240";// phone number to which SMS to be send
        String message="Este es un mensaje de prueba que será enviado mediante un servicio de alarma";// message to send
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumberReciver, null, message, null, null);
        // Show the toast  like in above screen shot
        Toast.makeText(context, "Alarma Activada. SMS Enviado", Toast.LENGTH_LONG).show();
    }
}
