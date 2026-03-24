package com.example.fragmentsub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SummaryFragment extends Fragment {

    private static final String ARG_ID = "arg_id";
    private static final String ARG_LOGIN = "arg_login";
    private static final String ARG_PASSWORD = "arg_password";
    private static final String ARG_NOM = "arg_nom";
    private static final String ARG_PRENOM = "arg_prenom";
    private static final String ARG_DATE_NAISSANCE = "arg_date_naissance";
    private static final String ARG_TELEPHONE = "arg_telephone";
    private static final String ARG_EMAIL = "arg_email";
    private static final String ARG_ADRESSE = "arg_adresse";
    private static final String ARG_INTERETS = "arg_interets";

    public SummaryFragment() {
        // Required empty public constructor
    }

    public static SummaryFragment newInstance(User user, long id) {
        SummaryFragment fragment = new SummaryFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_ID, id);
        args.putString(ARG_LOGIN, user.getLogin());
        args.putString(ARG_PASSWORD, user.getPassword());
        args.putString(ARG_NOM, user.getNom());
        args.putString(ARG_PRENOM, user.getPrenom());
        args.putString(ARG_DATE_NAISSANCE, user.getDateNaissance());
        args.putString(ARG_TELEPHONE, user.getTelephone());
        args.putString(ARG_EMAIL, user.getEmail());
        args.putString(ARG_ADRESSE, user.getAdresse());
        args.putString(ARG_INTERETS, user.getCentresInteret());
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_summary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle args = getArguments();
        if (args == null) {
            return;
        }

        ((TextView) view.findViewById(R.id.tv_saved_id)).setText(
                getString(R.string.summary_saved_id, args.getLong(ARG_ID))
        );
        ((TextView) view.findViewById(R.id.tv_login)).setText(
                getString(R.string.summary_login, args.getString(ARG_LOGIN, ""))
        );
        ((TextView) view.findViewById(R.id.tv_password)).setText(
                getString(R.string.summary_password, args.getString(ARG_PASSWORD, ""))
        );
        ((TextView) view.findViewById(R.id.tv_nom)).setText(
                getString(R.string.summary_nom, args.getString(ARG_NOM, ""))
        );
        ((TextView) view.findViewById(R.id.tv_prenom)).setText(
                getString(R.string.summary_prenom, args.getString(ARG_PRENOM, ""))
        );
        ((TextView) view.findViewById(R.id.tv_date_naissance)).setText(
                getString(R.string.summary_date_naissance, args.getString(ARG_DATE_NAISSANCE, ""))
        );
        ((TextView) view.findViewById(R.id.tv_telephone)).setText(
                getString(R.string.summary_telephone, args.getString(ARG_TELEPHONE, ""))
        );
        ((TextView) view.findViewById(R.id.tv_email)).setText(
                getString(R.string.summary_email, args.getString(ARG_EMAIL, ""))
        );
        ((TextView) view.findViewById(R.id.tv_adresse)).setText(
                getString(R.string.summary_adresse, args.getString(ARG_ADRESSE, ""))
        );
        ((TextView) view.findViewById(R.id.tv_interets)).setText(
                getString(R.string.summary_interets, args.getString(ARG_INTERETS, ""))
        );
    }
}

