package com.navermovie.presentation.view.bookmark

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.navermovie.entity.Movie
import com.navermovie.presentation.R
import com.navermovie.presentation.base.BaseFragment
import com.navermovie.presentation.databinding.FragmentBookmarkBinding
import com.navermovie.presentation.view.bookmark.adapter.BookmarkAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookmarkFragment : BaseFragment<FragmentBookmarkBinding>(R.layout.fragment_bookmark) {
    private val bookmarkViewModel: BookmarkViewModel by viewModels()
    private val bookmarkAdapter: BookmarkAdapter by lazy {
        BookmarkAdapter(itemClickListener = { movie ->
            doOnClick(movie)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        collectFlow()
        bookmarkViewModel.getAllList()
    }

    private fun collectFlow() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                bookmarkViewModel.bookmarkList.collect { list ->
                    bookmarkAdapter.submitList(list.toList())
                }
            }
        }
    }

    private fun initView() {
        binding.apply {
            ivBookMarkBack.setOnClickListener {
                requireView().findNavController().popBackStack()
            }
            rvBookMark.apply {
                layoutManager = GridLayoutManager(requireContext(), 3)
                adapter = bookmarkAdapter
            }
        }
        applyDebounce()
    }

    private fun applyDebounce() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                binding.etBookMarkSearch.debounceSearch().debounce(200L)
                    .collectLatest { charSequenece ->1
                        if (charSequenece.isNullOrBlank()) {
                            bookmarkViewModel.getAllList()
                        } else {
                            bookmarkViewModel.getSearchList(charSequenece.toString())
                        }
                    }
            }
        }
    }

    private fun doOnClick(movie: Movie) {
        val action = BookmarkFragmentDirections.actionBookMarkFragmentToMovieDetailFragment(
            movie
        )
        requireView().findNavController().navigate(action)
    }
}

fun EditText.debounceSearch(): Flow<CharSequence?> {
    return callbackFlow {
        val listener = doOnTextChanged { text, _, _, _ -> trySend(text) }
        awaitClose { removeTextChangedListener(listener) }
    }.onStart { emit(text) }
}