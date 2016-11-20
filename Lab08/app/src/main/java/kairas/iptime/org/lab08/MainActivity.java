package kairas.iptime.org.lab08;

public class MainActivity extends AppCpmpatActivity {

    EditText etUrl;
    Button btnRequest;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUrl = (EditText) findViewById(R.id.et_url_input);
        btnRequest = (Button) findViewById(R.id.btn_request);
        tvResult = (TextView) findViewById(R.id.tv_result_view);

        btnRequest.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = etUrl.getText().toString();
                DownloadTask downloadTask = new DownloadTask();
                downloadTask.execute(url);
            }
        });
    }

    private String downloadUrl(String strUrl) throws IOException {
        String s = null;
        byte[] buffer = new byte[3000];
        InputStream iStream = null;

        try {
            URL url = new URL(strUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.oepnConnection();

            urlConnection.connet();

            iStream = urlConnection.getInputStream();
            iStream.read(buffer);
            s = new String(buffer);
        } catch (Exception e) {
            Log.d("Exception occurred", e.toString());
            ] finally {
                iStream.close();
            }

            return s;
        }

        private class DownloadTask extends AsyncTask<String, Integer, String> {

            String s = null;

            @Override
            protected String doInBackground(String... url) {

                try {

                    s = downloadUrl(url[0]);

                } catch (Exception e) {
                    Log.d("Background Task", e.toString());
                }

                return s;
            }

            @Override
            protected void onPostExecute(String result) {
                tvResult setText(result);
            }
        }
    }