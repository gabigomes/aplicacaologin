package com.example.gabi.aplicacaologin.Activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import com.example.gabi.aplicacaologin.*;


public class InicioActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inicio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View perfilView = navigationView.getHeaderView(0);

        String nomeFromIntent = getIntent().getStringExtra("NOME");
        TextView nomePerfil = (TextView) perfilView.findViewById(R.id.textViewNamePerfil);
        nomePerfil.setText(nomeFromIntent);

        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        TextView emailPerfil = (TextView) perfilView.findViewById(R.id.textViewEmailPerfil);
        emailPerfil.setText(emailFromIntent);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        FragmentManager fragmento = getSupportFragmentManager();

        if (id == R.id.nav_jogos) {

            fragmento.beginTransaction().replace(R.id.tela, new Jogos()).commit();

        } else if (id == R.id.nav_pacientes) {

            fragmento.beginTransaction().replace(R.id.tela, new Pacientes()).commit();

        } else if (id == R.id.nav_relatorios) {

            fragmento.beginTransaction().replace(R.id.tela, new Relatorios()).commit();

        } else if (id == R.id.nav_consultas) {

            fragmento.beginTransaction().replace(R.id.tela, new Consultas()).commit();

        } else if (id == R.id.nav_notificacao) {

            fragmento.beginTransaction().replace(R.id.tela, new Notificacao()).commit();

        } else if (id == R.id.nav_configuracao) {

            fragmento.beginTransaction().replace(R.id.tela, new Configuracao()).commit();
        } else if (id == R.id.nav_contato) {

            fragmento.beginTransaction().replace(R.id.tela, new Contato()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
