package net.iquesoft.iquephoto.presentation.presenter.fragment;

import net.iquesoft.iquephoto.common.BaseFragmentPresenter;
import net.iquesoft.iquephoto.core.EditorCommand;
import net.iquesoft.iquephoto.presentation.view.fragment.TransformStraightenView;

public interface TransformStraightenPresenter extends BaseFragmentPresenter<TransformStraightenView> {
    void setupTransform(EditorCommand editorCommand);
}