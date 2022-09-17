package hk.com.chiefgroup.chiefx.journeys.explorecenter.views

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCategory
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreCenterState
import hk.com.chiefgroup.chiefx.journeys.explorecenter.datatypes.ExploreItem
import hk.com.chiefgroup.chiefx.journeys.explorecenter.thunk.ExploreCenterSelectedCategoryThunk
import hk.com.chiefgroup.chiefx.journeys.explorecenter.thunk.ExploreCenterSelectedItemThunk
import hk.com.chiefgroup.chiefx.module.core.baseclasses.Dispatcher
import hk.com.chiefgroup.chiefx.module.core.baseclasses.ObservableState
import org.koin.androidx.compose.get
import org.rekotlin.Action


@Composable
fun ExploreCenterCategoriesView(
    categories: List<ExploreCategory>,
    dispatcher: Dispatcher = get<ObservableState<ExploreCenterState>>()
) {
    CategoryContentListview(
        categories = categories,
        modifier = Modifier,
        onTitleClicked = {
            dispatcher.dispatch(
                ExploreCenterSelectedCategoryThunk(
                    it,
                    dispatcher
                )
            )
        },
        onItemClick = {
            dispatcher.dispatch(ExploreCenterSelectedItemThunk(it, dispatcher))
        })
}

@Composable
private fun CategoryContentListview(
    categories: List<ExploreCategory>, modifier: Modifier,
    onTitleClicked: (ExploreCategory) -> Unit,
    onItemClick: (ExploreItem) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(categories) { category ->
            Text(text = category.Name ?: "", modifier = Modifier
                .padding(top = 4.dp, bottom = 4.dp)
                .fillMaxSize()
                .clickable {
                    onTitleClicked(category)
                })
            ExploreCenterItemHorizontalListView(category, onItemClick = { onItemClick(it) })
        }
    }
}
class MockDispatcher(): Dispatcher {
    override fun dispatch(action: Action) {
        println("$action")
    }

    override fun pop() {
        println("pop")
    }

    override fun push(segment: String) {
        println("$segment")
    }

}
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ExploreCenterCategoriesViewPreview() {
    ExploreCenterCategoriesView(
        listOf(mockCategory(), mockCategory()),
        MockDispatcher()
    )
}