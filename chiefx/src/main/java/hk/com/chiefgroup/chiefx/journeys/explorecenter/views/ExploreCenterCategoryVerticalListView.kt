package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCategory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.viewmodel.ExploreCenterStateObservableStateViewModel
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableStateViewModel


@Composable
fun ExploreCenterCategoryVerticalListView(records: List<ExploreCategory>, viewModel: ObservableStateViewModel<ExploreCenterState>) {
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(records) { item ->  
            ExploreCenterItemHorizontalListView(item, viewModel)
        }
    }
}