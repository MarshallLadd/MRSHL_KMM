package me.pm.marshall.ladd.mrshl.android.presentation.core.composables

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Preview(device = Devices.PIXEL, showSystemUi = true, showBackground = true)
@Preview(device = Devices.PIXEL_2_XL, showSystemUi = true, showBackground = true)
@Preview(device = Devices.FOLDABLE, showSystemUi = true)
@Preview(device = Devices.TABLET, showSystemUi = true)
annotation class PreviewDevices
