package org.iptime.kairas.hw07;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class Web extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);

        final EditText editText = (EditText) findViewById(R.id.url);
        final WebView webView = (WebView) findViewById(R.id.webview);

        final Button move = (Button) findViewById(R.id.btnMove);

        final Button home = (Button) findViewById(R.id.btnHome);
        final Button back = (Button) findViewById(R.id.btnBack);
        final Button forward = (Button) findViewById(R.id.btnForward);
        final Button stop = (Button) findViewById(R.id.btnStop);
        final Button refresh = (Button) findViewById(R.id.btnRefresh);

        final ProgressBar pbar = (ProgressBar)findViewById(R.id.progressBar);
        pbar.setVisibility(View.GONE);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.naver.com";

                if(!url.contains("http://") && (!url.contains("https://")))
                    url="http://" + url;

                webView.loadUrl(url);
            }
        });

        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editText.getText().toString();

                if(!url.contains("http://") && (!url.contains("https://")))
                    url="http://" + url;

                webView.loadUrl(url);
            }
        });

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

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.stopLoading();
            }
        });

        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = editText.getText().toString();
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