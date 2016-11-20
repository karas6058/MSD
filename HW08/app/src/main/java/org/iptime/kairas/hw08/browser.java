package org.iptime.kairas.hw08;

import android.app.Activity;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class browser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);



        final EditText editText = (EditText) findViewById(R.id.url);
        final WebView webView = (WebView) findViewById(R.id.webview);

        final ImageButton back = (ImageButton) findViewById(R.id.btnBack);
        final ImageButton forward = (ImageButton) findViewById(R.id.btnForward);
        final ImageButton stop = (ImageButton) findViewById(R.id.btnStop);
        final ImageButton refresh = (ImageButton) findViewById(R.id.btnRefresh);

        final ProgressBar pbar = (ProgressBar)findViewById(R.id.progressBar);
        pbar.setVisibility(View.GONE);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.goBack();
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.goForward();
            }
        });

        forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.stopLoading();
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editText.getText().toString();

                if(!url.contains("http://") && (!url.contains("https://")))
                    url="http://" + url;

                webView.loadUrl(url);
            }
        });

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageFinished(WebView view, String url) {
                editText.setText(url);
                setTitle(view.getTitle());
                pbar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pbar.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }
        });
    }
}
