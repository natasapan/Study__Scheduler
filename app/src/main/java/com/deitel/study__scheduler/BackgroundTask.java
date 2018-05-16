package com.deitel.study__scheduler;

import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by natas on 5/9/2018.
 */

public class BackgroundTask extends AsyncTask<String, Void, String>{

    Context c;

    BackgroundTask(Context c){

        this.c = c;
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

   /* @Override
    protected String doInBackground(String... params) {

        String method = params[0];
        String reg_url = "http://10.0.2.2/StudyScheduler/register.php";
        String sign_in_url = "http://10.0.2.2/StudyScheduler/sign_in.php";

        if(method.equals("register")){

            String name = params[1];
            String username = params[2];
            String password = params[3];

            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));

                String data = URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("user_name", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();

                return  "Registration successful";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }


    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(c,result, Toast.LENGTH_LONG).show();
    }-*/
}

