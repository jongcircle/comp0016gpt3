package com.example.gptdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Environment;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private String url = "https://craiyon.com";

    TextView textView, clipboard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        setContentView(R.layout.activity_main);

        textView = (TextView)findViewById(R.id.aliceText);
        textView.setMovementMethod(new ScrollingMovementMethod());
        InputStream inputStream = this.getResources().openRawResource(R.raw.alice);
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( inputStream ));
        StringBuffer stringBuffer = new StringBuffer();
        String strData = "";
        if (inputStream!=null){
            try {
                while ((strData = bufferedReader.readLine()) != null){
                    stringBuffer.append( strData + "\n");
                }
                textView.setText(stringBuffer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

//        clipboard.setOnLongClickListener(new View.OnLongClickListener(){
//            @Override
//            public boolean onLongClick(View view){
//                String text = clipboard.getText().toString();
//                createClipData(text);
//                return false;
//            }
//        });

        webView = (WebView)findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClientClass());

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    private class WebViewClientClass extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

//    public void createClipData(String message){
//        ClipboardManager clipboardManager = (ClipboardManager) getApplicationContext().getSystemService(getApplicationContext().CLIPBOARD_SERVICE);
//        ClipData clipData = ClipData.newPlainText("message", message);
//        clipboardManager.setPrimaryClip(clipData);
//        Toast.makeText(this, "copy", Toast.LENGTH_SHORT).show();
//    }

    // Elynor's help code
//    private void copyToClipboard(String string) {
//        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
//        ClipData clip = ClipData.newPlainText("MotionInput", string);
//        clipboard.setPrimaryClip(clip);
//    }



}