package example.codeclan.com.fruitmachine.adapters;

import android.databinding.BindingAdapter;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;

import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;

/**
 * Created by user on 11/07/2017.
 */

public class ReelStripViewModelAdapter<ReelStripViewModel> extends BindingRecyclerViewAdapter<ReelStripViewModel>
{
    public static final String TAG = "RecyclerView";

    @Override
    public ViewDataBinding onCreateBinding(LayoutInflater inflater, @LayoutRes int layoutId, ViewGroup viewGroup) {
        ViewDataBinding binding = super.onCreateBinding(inflater, layoutId, viewGroup);

        return binding;
    }

    @Override
    public void onBindBinding(ViewDataBinding binding, int variableId, @LayoutRes int layoutRes, int position, ReelStripViewModel item) {
        super.onBindBinding(binding, variableId, layoutRes, position, item);
    }
}
