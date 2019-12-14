package in.countrybaskets.countrybaskets;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebViewClient;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
WebView wv;
ProgressBar pb;
ImageView image;
String URL1="http://countrybaskets.in";
    SwipeRefreshLayout mySwipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Data...");
        progressDialog.setCancelable(false);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final WebView wv = (WebView)findViewById(R.id.webview);
        final Intent i = new Intent(MainActivity.this,Home.class);
        WebSettings webSettings = wv.getSettings();
        wv.getSettings().setBuiltInZoomControls(false);
        wv.getSettings().setDisplayZoomControls(false);
        webSettings.setJavaScriptEnabled(true);

        final Intent intent = getIntent();
        String str = intent.getStringExtra("message");
        URL1=str;
        wv.loadUrl(URL1);
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


        mySwipeRefreshLayout = (SwipeRefreshLayout)this.findViewById(R.id.swipeContainer);

        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        wv.reload();
                        wv.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
// This is important as it forces webview to load from the instead of reloading from cache

                        wv .loadUrl(URL1);

                    }
                }
        );
       wv.setOnKeyListener(new View.OnKeyListener(){
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (wv.canGoBack()) {
                    if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == MotionEvent.ACTION_UP) {
                        wv.goBack();
                        return true;
                    }
                }
                else {
                    startActivity(i);
                }

                return true ;
            }
       });
        wv.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress < 67) {
                    progressDialog.show();

                }
                if (progress > 67) {
                    progressDialog.dismiss();
                    mySwipeRefreshLayout.setRefreshing(false);
                }
            }
        });

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebView wv = (WebView)findViewById(R.id.webview);
                WebSettings webSettings = wv.getSettings();
                webSettings.setJavaScriptEnabled(true);
                wv.loadUrl("http://countrybaskets.in/");
                wv.setWebViewClient(new MyAppWebViewClient());
                wv.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        return false;
                    }
                });
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    public void onError(){
        wv.loadUrl("file:///android_asset/gif.html");
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
            moveTaskToBack(true);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            WebView wv = (WebView)findViewById(R.id.webview);
            WebSettings webSettings = wv.getSettings();
            webSettings.setJavaScriptEnabled(true);
            wv.loadUrl("http://countrybaskets.in/my-account/");
            wv.setWebViewClient(new MyAppWebViewClient());
            wv.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }
            });
            return true;
        }else if (id == R.id.register) {
            WebView wv = (WebView)findViewById(R.id.webview);
            WebSettings webSettings = wv.getSettings();
            webSettings.setJavaScriptEnabled(true);
            wv.loadUrl("http://countrybaskets.in/registration/");
            wv.setWebViewClient(new MyAppWebViewClient());
            wv.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }
            });
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.vegetables) {
            WebView wv = (WebView)findViewById(R.id.webview);
            WebSettings webSettings = wv.getSettings();
            webSettings.setJavaScriptEnabled(true);
            wv.loadUrl("http://countrybaskets.in/product-category/clean-and-freshly-packed-vegetables/");
            wv.setWebViewClient(new MyAppWebViewClient());
            wv.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }
            });
            // Handle the camera action
        }else if (id == R.id.dry_fruits) {
            WebView wv = (WebView)findViewById(R.id.webview);
            WebSettings webSettings = wv.getSettings();
            webSettings.setJavaScriptEnabled(true);
            wv.loadUrl("http://countrybaskets.in/product-category/dry-fruits/");
            wv.setWebViewClient(new MyAppWebViewClient());
            wv.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }
            });
            // Handle the camera action
        } else if (id == R.id.fruits) {
            WebView wv = (WebView)findViewById(R.id.webview);
            WebSettings webSettings = wv.getSettings();
            webSettings.setJavaScriptEnabled(true);
            wv.loadUrl("http://countrybaskets.in/product-category/all-categories-of-fruits/");
            wv.setWebViewClient(new MyAppWebViewClient());
            wv.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }
            });
        } else if (id == R.id.gift_pack) {
            WebView wv = (WebView)findViewById(R.id.webview);
            WebSettings webSettings = wv.getSettings();
            webSettings.setJavaScriptEnabled(true);
            wv.loadUrl("http://countrybaskets.in/product-category/a-gift-pack-of-fruits/");
            wv.setWebViewClient(new MyAppWebViewClient());
            wv.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }
            });

        } else if (id == R.id.Groceries) {
            WebView wv = (WebView)findViewById(R.id.webview);
            WebSettings webSettings = wv.getSettings();
            webSettings.setJavaScriptEnabled(true);
            wv.loadUrl("http://countrybaskets.in/product-category/retail-foodstuffs-and-other-household-supplies/");
            wv.setWebViewClient(new MyAppWebViewClient());
            wv.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }
            });

        }

        else if (id == R.id.flours) {
            WebView wv = (WebView)findViewById(R.id.webview);
            WebSettings webSettings = wv.getSettings();
            webSettings.setJavaScriptEnabled(true);
            wv.loadUrl("http://countrybaskets.in/product-category/retail-foodstuffs-and-other-household-supplies/atta-other-flours/");
            wv.setWebViewClient(new MyAppWebViewClient());
            wv.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }
            });
        } else if (id == R.id.kids_products) {
            WebView wv = (WebView)findViewById(R.id.webview);
            WebSettings webSettings = wv.getSettings();
            webSettings.setJavaScriptEnabled(true);
            wv.loadUrl("http://countrybaskets.in/product-category/retail-foodstuffs-and-other-household-supplies/the-kids-and-babies-essential-products/");
            wv.setWebViewClient(new MyAppWebViewClient());
            wv.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }
            });
        }else if (id == R.id.soft_drinks) {
            WebView wv = (WebView)findViewById(R.id.webview);
            WebSettings webSettings = wv.getSettings();
            webSettings.setJavaScriptEnabled(true);
            wv.loadUrl("http://countrybaskets.in/product-category/retail-foodstuffs-and-other-household-supplies/all-types-of-soft-drinks/");
            wv.setWebViewClient(new MyAppWebViewClient());
            wv.setWebViewClient(new WebViewClient() {
                @Override
                // public void onPageFinished(WebView view, String url) {
                //hide loading image
                //     findViewById(R.id.image).setVisibility(View.GONE);
                //show webview
                //     findViewById(R.id.webview).setVisibility(View.VISIBLE);
                //}

                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }
            });
        }
        else if (id == R.id.chocolates_and_biscuits) {
            WebView wv = (WebView)findViewById(R.id.webview);
            WebSettings webSettings = wv.getSettings();
            webSettings.setJavaScriptEnabled(true);
            wv.loadUrl("http://countrybaskets.in/product-category/retail-foodstuffs-and-other-household-supplies/all-types-biscuits-snacks-and-choclates/");
            wv.setWebViewClient(new MyAppWebViewClient());
            wv.setWebViewClient(new WebViewClient() {
                @Override
                // public void onPageFinished(WebView view, String url) {
                //hide loading image
                //     findViewById(R.id.image).setVisibility(View.GONE);
                //show webview
                //     findViewById(R.id.webview).setVisibility(View.VISIBLE);
                //}

                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }
            });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
