package com.antonious.kotlincomposesample.ui.login


import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.antonious.kotlincomposesample.model.Credentials

@Composable
fun LoginForm(onLogin: (Credentials) -> Unit) {
  Surface {
    var credentials by remember { mutableStateOf(Credentials()) }
    val context = LocalContext.current

    Column(
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally,
      modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 30.dp)
    ) {
      LoginField(
        value = credentials.userName,
        onChange = { data -> credentials = credentials.copy(userName = data) },
        modifier = Modifier.fillMaxWidth()
      )
      PasswordField(
        value = credentials.pwd,
        onChange = { data -> credentials = credentials.copy(pwd = data) },
        submit = {
          if (!checkCredentials(onLogin, credentials, context)) credentials = Credentials()
        },
        modifier = Modifier.fillMaxWidth()
      )
      Spacer(modifier = Modifier.height(10.dp))

      Spacer(modifier = Modifier.height(20.dp))
      Button(
        onClick = {
          if (!checkCredentials(onLogin, credentials, context)) credentials = Credentials()
        },
        enabled = credentials.isNotEmpty(),
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Text("Login")
      }
    }
  }
}

fun checkCredentials(
  onLogin: (Credentials) -> Unit,
  creds: Credentials,
  context: Context
): Boolean {
  if (creds.isNotEmpty()) {
    onLogin.invoke(creds)
    return true
  } else {
    Toast.makeText(context, "Wrong Credentials", Toast.LENGTH_SHORT).show()
    return false
  }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginField(
  value: String,
  onChange: (String) -> Unit,
  modifier: Modifier = Modifier,
  label: String = "Login",
  placeholder: String = "Enter your Username"
) {

  val focusManager = LocalFocusManager.current
  val leadingIcon = @Composable {
    Icon(
      Icons.Default.Person,
      contentDescription = "",
      tint = MaterialTheme.colorScheme.primary
    )
  }

  TextField(
    value = value,
    onValueChange = onChange,
    modifier = modifier,
    leadingIcon = leadingIcon,
    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
    keyboardActions = KeyboardActions(
      onNext = { focusManager.moveFocus(FocusDirection.Down) }
    ),
    placeholder = { Text(placeholder) },
    label = { Text(label) },
    singleLine = true,
    visualTransformation = VisualTransformation.None
  )
}

@Composable
fun PasswordField(
  value: String,
  onChange: (String) -> Unit,
  submit: () -> Unit,
  modifier: Modifier = Modifier,
  label: String = "Password",
  placeholder: String = "Enter your Password"
) {

  var isPasswordVisible by remember { mutableStateOf(false) }

  val leadingIcon = @Composable {
    Icon(
      Icons.Default.Key,
      contentDescription = "",
      tint = MaterialTheme.colorScheme.primary
    )
  }
  val trailingIcon = @Composable {
    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
      Icon(
        if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
        contentDescription = "",
        tint = MaterialTheme.colorScheme.primary
      )
    }
  }


  TextField(
    value = value,
    onValueChange = onChange,
    modifier = modifier,
    leadingIcon = leadingIcon,
    trailingIcon = trailingIcon,
    keyboardOptions = KeyboardOptions(
      imeAction = ImeAction.Done,
      keyboardType = KeyboardType.Password
    ),
    keyboardActions = KeyboardActions(
      onDone = { submit() }
    ),
    placeholder = { Text(placeholder) },
    label = { Text(label) },
    singleLine = true,
    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
  )
}
