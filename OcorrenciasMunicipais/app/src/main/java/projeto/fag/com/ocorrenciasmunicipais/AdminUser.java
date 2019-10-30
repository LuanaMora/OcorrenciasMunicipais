package projeto.fag.com.ocorrenciasmunicipais;

import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.gson.Gson;
import java.util.List;
import java.util.concurrent.ExecutionException;
import projeto.fag.com.ocorrenciasmunicipais.model.Usuario;
import projeto.fag.com.ocorrenciasmunicipais.task.Result;
import projeto.fag.com.ocorrenciasmunicipais.task.Task;

public class AdminUser extends AppCompatActivity {

    private Spinner spUsuarioAdmin;
    private Button btSalvarAdmin;
    private Usuario usuarioEncontrado = null;

    private ArrayAdapter<Usuario> adminAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_user);
        loadEvents();

    }

    private void loadEvents() {
        loadComponents();
        loadSpinner();
        saveAdmin();
    }

    private void loadComponents() {
        spUsuarioAdmin = findViewById(R.id.spUsuarioAdmin);
        btSalvarAdmin = findViewById(R.id.btSalvarAdmin);

    }

    private void loadSpinner() {
        List<Usuario> usuarioList = Usuario.listAll(Usuario.class);
        adminAdapter = new ArrayAdapter<>(AdminUser.this, R.layout.support_simple_spinner_dropdown_item, usuarioList);
        spUsuarioAdmin.setAdapter(adminAdapter);

    }

    private void saveAdmin() {
        btSalvarAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Usuario> findUsuario = Usuario.find(Usuario.class, "cd_usuario =" +
                        " '" + (spUsuarioAdmin.getSelectedItemId() + 1) + "'", null, null, null, null);
                if (!findUsuario.isEmpty()) {
                    for (Usuario u : findUsuario) {
                        if (findUsuario.contains(u)) {
                            usuarioEncontrado = u;
                        }
                    }
                }
                if (usuarioEncontrado != null) {
                    MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(AdminUser.this, R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog);
                    dialog.setTitle("Atenção");
                    dialog.setMessage("Deseja adicionar " + usuarioEncontrado.getNmUsuario() + " como administrador?");
                    dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                String codigoEncontrado = String.valueOf(usuarioEncontrado.getCdUsuario());
                                usuarioEncontrado.setStAdministrador(true);
                                Task task = new Task(AdminUser.this);
                                Result result = result = task.executeOnExecutor
                                        (AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"Usuarios", "PUT", new Gson().toJson(usuarioEncontrado),codigoEncontrado,}).get();
                            } catch (ExecutionException e) {
                                e.printStackTrace();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                    });
                    dialog.setNegativeButton("Não", null);
                    dialog.show();
                } else {
                    MaterialAlertDialogBuilder dialog = new MaterialAlertDialogBuilder(AdminUser.this, R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog);
                    dialog.setTitle("Erro");
                    dialog.setMessage("Usuário não encontrado! ");
                    dialog.setPositiveButton("continuar", null);
                    dialog.show();
                }
            }
        });
    }
}



