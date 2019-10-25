package projeto.fag.com.ocorrenciasmunicipais.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.List;
import projeto.fag.com.ocorrenciasmunicipais.R;
import projeto.fag.com.ocorrenciasmunicipais.model.Usuario;

public class AdminUserAdapter extends BaseAdapter {
    LayoutInflater myInflater;
    List<Usuario> usuarioList;

    public AdminUserAdapter(Context context, List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
        myInflater = LayoutInflater.from(context); //Responsavel por inflar o layout
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Usuario usuario = usuarioList.get(position);
        view = myInflater.inflate(R.layout.item_admin, null);

        return view;
    }
}

/*
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Corretor corretor = corretorList.get(position);
        view = myInflater.inflate(R.layout.item_imovel, null);
        ((TextView) view.findViewById(R.id.etCodigo)).setText(String.valueOf(corretor.getCodigo()));
        ((TextView) view.findViewById(R.id.etNome)).setText(String.valueOf(corretor.getNome()));
        ((TextView) view.findViewById(R.id.etTelefone)).setText(String.valueOf(corretor.getTelefone()));
        ((TextView) view.findViewById(R.id.etCreci)).setText(String.valueOf(corretor.getCreci()));

        return view;
    }
*/