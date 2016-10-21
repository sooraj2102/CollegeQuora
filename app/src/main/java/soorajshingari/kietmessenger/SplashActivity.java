package soorajshingari.kietmessenger;

        import android.animation.AnimatorSet;
        import android.animation.ObjectAnimator;
        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.os.Handler;
        import android.view.View;
        import android.view.animation.AccelerateDecelerateInterpolator;
        import android.view.animation.Animation;
        import android.view.animation.AnimationUtils;
        import android.widget.ImageView;

        import soorajshingari.kietmessenger.*;
        import soorajshingari.kietmessenger.*;

public class SplashActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 20000;
    private ImageView mKenBurns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setAnimation();

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        mKenBurns = (ImageView) findViewById(R.id.image);

        mKenBurns.setImageResource(R.drawable.collegequora);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, dashboard.class);
                startActivity(i);

                finish();
            }
        }, SPLASH_TIME_OUT);
    }

    private void setAnimation() {
        ObjectAnimator scaleXAnimation = ObjectAnimator.ofFloat(findViewById(R.id.welcome_text), "scaleX", 5.0F, 1.0F);
        scaleXAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleXAnimation.setDuration(1200);
        ObjectAnimator scaleYAnimation = ObjectAnimator.ofFloat(findViewById(R.id.welcome_text), "scaleY", 5.0F, 1.0F);
        scaleYAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        scaleYAnimation.setDuration(1200);
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(findViewById(R.id.welcome_text), "alpha", 0.0F, 1.0F);
        alphaAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        alphaAnimation.setDuration(1200);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(scaleXAnimation).with(scaleYAnimation).with(alphaAnimation);
        animatorSet.setStartDelay(500);
        animatorSet.start();

        findViewById(R.id.image).setAlpha(1.0F);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.translate_top_to_centre);
        findViewById(R.id.image).startAnimation(anim);
    }
}
