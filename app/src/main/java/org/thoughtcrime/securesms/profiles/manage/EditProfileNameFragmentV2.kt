/*
 * Copyright 2025 Signal Messenger, LLC
 * SPDX-License-Identifier: AGPL-3.0-only
 */

package org.thoughtcrime.securesms.profiles.manage

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import org.signal.core.ui.compose.Buttons
import org.signal.core.ui.compose.Scaffolds
import org.signal.core.ui.compose.TextFields
import org.thoughtcrime.securesms.R
import org.thoughtcrime.securesms.compose.ComposeFragment
import org.thoughtcrime.securesms.recipients.Recipient.Companion.self
import org.thoughtcrime.securesms.util.viewModel

class EditProfileNameFragmentV2: ComposeFragment() {
  @Composable
  override fun FragmentContent() {
    val navController: NavController = remember { findNavController() }

    val viewModel by viewModel {
      EditProfileNameViewModel()
    }

    val context: Context = LocalContext.current

    var givenName by remember { mutableStateOf(self().profileName.givenName) }
    var familyName by remember { mutableStateOf(self().profileName.familyName) }

    Scaffolds.Settings(
      title = stringResource(R.string.EditProfileNameFragment_your_name),
      onNavigationClick = { navController.popBackStack() },
      navigationIcon = ImageVector.vectorResource(id = R.drawable.ic_x),
      navigationContentDescription = stringResource(id = R.string.Material3SearchToolbar__close)
    ) { contentPadding ->
      Column(
        modifier = Modifier
          .padding(contentPadding)
          .padding(horizontal = dimensionResource(org.signal.core.ui.R.dimen.gutter))
          .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
      ) {

        TextFields.TextField(
          modifier = Modifier
            .fillMaxWidth(),
          label = { Text(stringResource(R.string.EditProfileNameFragment_first_name)) },
          value = givenName,
          onValueChange = { givenName = it },
          singleLine = true
        )

        TextFields.TextField(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp),
          label = { Text(stringResource(R.string.EditProfileNameFragment_last_name_optional)) },
          value = familyName,
          onValueChange = { familyName = it },
          singleLine = true
        )

        Box(
          modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .padding(bottom = 24.dp)
        ) {
          Buttons.LargeTonal(
            onClick = {
              viewModel.onSaveClicked(context, givenName, familyName)
              navController.popBackStack()
            },
            modifier = Modifier.align(Alignment.BottomEnd)
          ) {
            Text(
              text = stringResource(R.string.EditProfileNameFragment_save)
            )
          }
        }
      }
    }
  }

}