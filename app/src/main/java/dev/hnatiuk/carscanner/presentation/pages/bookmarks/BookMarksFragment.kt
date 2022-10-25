package dev.hnatiuk.carscanner.presentation.pages.bookmarks

import android.annotation.SuppressLint
import dev.hnatiuk.carscanner.R
import dev.hnatiuk.carscanner.databinding.FragmentBookmarksBinding
import dev.hnatiuk.carscanner.presentation.pages.base.BaseFragment
import dev.hnatiuk.carscanner.presentation.pages.base.Depends

@SuppressLint("NonConstantResourceId")
@Depends(R.layout.fragment_bookmarks, BookMarksFragmentViewModel::class)
internal class BookMarksFragment :
    BaseFragment<FragmentBookmarksBinding, BookMarksFragmentViewModel>()