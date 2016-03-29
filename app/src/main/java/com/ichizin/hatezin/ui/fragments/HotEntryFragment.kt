package com.ichizin.hatezin.ui.fragments


import android.content.Context
import android.os.Bundle
import android.support.v7.widget.RecyclerView.LayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ichizin.hatezin.HatezinApplication
import com.ichizin.hatezin.R
import com.ichizin.hatezin.model.HatenaEntry
import com.ichizin.hatezin.presenter.HotEntryPresenter
import javax.inject.Inject

/**
 *
 *
 * @author ichizin
 */
class HotEntryFragment : BaseFragment(), HotEntryPresenter.HotEntryView {

    companion object {
        @JvmStatic
        fun newInstance(): HotEntryFragment {
            return HotEntryFragment()
        }
    }

    @Inject
    lateinit var presenter: HotEntryPresenter

    private var layoutManager: LayoutManager? = null


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        HatezinApplication.getComponent(this).inject(this);
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_recyclerview, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(savedInstanceState == null) {
            presenter.attachView(this)
            presenter.initialize()
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.resume()
    }

    override fun onPause() {
        super.onPause()
        presenter.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.destroy()
    }

    override fun renderData(feeds: MutableList<HatenaEntry>?) {
        throw UnsupportedOperationException()
    }
}
