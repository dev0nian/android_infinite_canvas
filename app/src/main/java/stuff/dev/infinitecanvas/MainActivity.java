package stuff.dev.infinitecanvas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    DrawingPad mDrawingPad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);

        mDrawingPad = findViewById(R.id.drawing_pad);

        Button moveButton = findViewById(R.id.move_button);
        if(moveButton != null) {
            moveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDrawingPad.setMode(DrawingPad.Mode.MODE_MOVE);
                }
            });
        }

        Button penButton = findViewById(R.id.pen_button);
        if(penButton != null) {
            penButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDrawingPad.setMode(DrawingPad.Mode.MODE_PEN);
                }
            });
        }

        SeekBar seekBar = findViewById(R.id.scale_seekbar);
        if(seekBar != null) {
            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                    float scale = i == 0 ? 1.0f : i;
                    scale /= 10.0f;
                    mDrawingPad.setScaleFactor(scale);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        }
    }
}
