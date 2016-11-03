package net.iquesoft.iquephoto.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import net.iquesoft.iquephoto.DataHolder;
import net.iquesoft.iquephoto.adapters.AdjustAdapter;
import net.iquesoft.iquephoto.R;
import net.iquesoft.iquephoto.common.BaseFragment;
import net.iquesoft.iquephoto.core.ImageEditorView;
import net.iquesoft.iquephoto.di.components.IEditorActivityComponent;
import net.iquesoft.iquephoto.model.Adjust;
import net.iquesoft.iquephoto.presenter.AdjustFragmentPresenterImpl;
import net.iquesoft.iquephoto.view.IAdjustmentFragmentView;
import net.iquesoft.iquephoto.view.IEditorActivityView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AdjustFragment extends BaseFragment implements IAdjustmentFragmentView {

    private List<Adjust> mAdjustList = Adjust.getAdjustList();

    private Unbinder mUnbinder;

    private AdjustAdapter mAdapter;

    @BindView(R.id.adjustRecyclerView)
    RecyclerView recyclerView;

    @Inject
    IEditorActivityView editorActivityView;

    @Inject
    AdjustFragmentPresenterImpl presenter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.getComponent(IEditorActivityComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_adjust, container, false);

        mUnbinder = ButterKnife.bind(this, v);

        mAdapter = new AdjustAdapter(mAdjustList);

        mAdapter.setAdjustListener(adjust -> {
            editorActivityView.getImageEditorView().setCommand(adjust.getTitle());

            editorActivityView.setupFragment(adjust.getFragment());
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(null, LinearLayout.HORIZONTAL, false));

        recyclerView.setAdapter(mAdapter);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.init(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}