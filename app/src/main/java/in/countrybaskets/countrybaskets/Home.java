package in.countrybaskets.countrybaskets;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

public class Home extends AppCompatActivity implements View.OnClickListener {
    ImageView Fruits,Dry_Fruits,Vegetables,OIl,Groceries,Rice;
    WebView wv;
    Button  closePopupBtn;
    FloatingActionButton showPopupBtn;
    PopupWindow popupWindow;
    RelativeLayout RelativeLayout1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ImageView Fruits = (ImageView) findViewById(R.id.fruits1);
        ImageView Dry_Fruits = (ImageView) findViewById(R.id.dry_fruits1);
        ImageView Vegetables = (ImageView) findViewById(R.id.vegetables1);
        ImageView OIl = (ImageView) findViewById(R.id.Oils);
        ImageView Groceries =(ImageView)findViewById(R.id.Groceries1);
        ImageView Rice = (ImageView) findViewById(R.id.Rices);
        Fruits.setOnClickListener(this);
        Dry_Fruits.setOnClickListener(this);
        Vegetables.setOnClickListener(this);
        OIl.setOnClickListener(this);
        Groceries.setOnClickListener(this);
        Rice.setOnClickListener(this);


            /*this is for popup window*/
        /*
        showPopupBtn = (FloatingActionButton) findViewById(R.id.showPopupBtn);
        RelativeLayout1 = (RelativeLayout) findViewById(R.id.RelativeLayout1);

        showPopupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //instantiate the popup.xml layout file
                LayoutInflater layoutInflater = (LayoutInflater) Home.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.activity_popup,null);

                closePopupBtn = (Button) customView.findViewById(R.id.closePopupBtn);

                //instantiate popup window
                popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                //display the popup window
                popupWindow.showAtLocation(RelativeLayout1, Gravity.CENTER, 0, 0);

                //close the popup window on button click
                closePopupBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

            }
        });
*/
         /*above is for popup view*/


        final WebView wv = (WebView)findViewById(R.id.promo);
        WebSettings webSettings = wv.getSettings();
        wv.getSettings().setBuiltInZoomControls(false);
        wv.getSettings().setDisplayZoomControls(false);
        webSettings.setJavaScriptEnabled(true);
        wv.loadUrl("http://countrybaskets.in/Slide");
        wv.setWebViewClient(new MyAppWebViewClient());
        wv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
            @Override
            public void onReceivedError(WebView view,int errorCode,String description,String failingUrl){
                wv.loadUrl("file:///android_asset/gif.html");
            }
        });

        Fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = "http://countrybaskets.in/product-category/all-categories-of-fruits/";

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("message", str);

                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.fruits1)
        {
            String str = "http://countrybaskets.in/product-category/all-categories-of-fruits/";
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("message", str);

            startActivity(intent);
        }
        else if (v.getId()==R.id.dry_fruits1)
        {
            String str = "http://countrybaskets.in/product-category/dry-fruits/";
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("message", str);

            startActivity(intent);
        }
        else if (v.getId()==R.id.vegetables1)
        {
            String str = "http://countrybaskets.in/product-category/clean-and-freshly-packed-vegetables/";
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("message", str);

            startActivity(intent);
        }
        else if (v.getId()==R.id.Oils)
        {
            String str = "http://countrybaskets.in/shop-2/?product_cat=edible-oils-or-eating-oil&s=&post_type=product";
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("message", str);

            startActivity(intent);
        }
        else if (v.getId()==R.id.Groceries1)
        {
            String str = "http://countrybaskets.in/product-category/retail-foodstuffs-and-other-household-supplies/";
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("message", str);

            startActivity(intent);
        }
        else if (v.getId()==R.id.Rices)
        {
            String str = "http://countrybaskets.in/shop-2/?product_cat=rice-and-other-grains&s=&post_type=product";
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("message", str);

            startActivity(intent);
        }

    }
}
