package in4matics_team.in4maticsquiz;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import in4matics_team.in4maticsquiz.R;

public class SearchDialog extends Dialog implements android.view.View.OnClickListener{

    private Context context;
    private Button btnSearch;

    public SearchDialog(Context context) {
        super(context);
        this.context = context;
        this.setTitle(R.string.search_dialog_title);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_dialog);
        btnSearch = (Button) findViewById(R.id.btnSearchDiscounts);
        btnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        EditText text = (EditText) findViewById(R.id.search_term);


    }
}