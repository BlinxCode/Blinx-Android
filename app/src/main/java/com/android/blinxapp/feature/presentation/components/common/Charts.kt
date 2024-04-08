package com.android.blinxapp.feature.presentation.components.common

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.android.blinxapp.R
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineDataSet


@Composable
fun Chart(
    modifier: Modifier = Modifier,
    lineDataSet: LineDataSet? = null,
) {
    AndroidView(
        factory = { context ->
            LineChart(context).apply {
                description.isEnabled = false
                isDragEnabled = false
                xAxis.isEnabled = false
                axisLeft.setDrawAxisLine(false)
                axisLeft.textColor = getChartAxisLeftTextColor(context)
                axisRight.isEnabled = false
                legend.isEnabled = false
                setTouchEnabled(false)
                setScaleEnabled(false)
                setDrawGridBackground(false)
                setDrawBorders(false)
                invalidate()

                //   setLineDataSet(lineDataSet = lineDataSet)
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(300.dp)
    )
}

private fun getChartAxisLeftTextColor(context: Context): Int =
    if (context.isSystemInDarkTheme()) {
        R.color.white
    } else {
        R.color.electricBlue
    }