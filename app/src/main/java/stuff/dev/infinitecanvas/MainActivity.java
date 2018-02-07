package stuff.dev.infinitecanvas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);

        final DrawingPad drawingPad = findViewById(R.id.drawing_pad);

        Button moveButton = findViewById(R.id.move_button);
        if(moveButton != null) {
            moveButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drawingPad.setMode(DrawingPad.Mode.MODE_MOVE);
                }
            });
        }

        Button penButton = findViewById(R.id.pen_button);
        if(penButton != null) {
            penButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    drawingPad.setMode(DrawingPad.Mode.MODE_PEN);
                }
            });
        }
    }
}
