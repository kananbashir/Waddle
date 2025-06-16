package com.waddleup.settings.presentation.model

import androidx.annotation.StringRes
import com.waddleup.app.theme.R

/**
 * Created on 6/15/2025
 * @author Kanan Bashir
 */

enum class SettingsActionType {
    Navigate,
    Expand,
    Switch
}

enum class SettingsAction(val item: SettingsActionItem) {
    EditProfile(
        SettingsActionItem(
            id = 1,
            icon = R.drawable.ic_edit,
            title = R.string.text_edit_profile,
            actionType = SettingsActionType.Navigate
        )
    ),
    ChangePassword(
        SettingsActionItem(
            id = 2,
            icon = R.drawable.ic_lock,
            title = R.string.text_change_password,
            actionType = SettingsActionType.Expand
        )
    ),
    ChangePinCode(
        SettingsActionItem(
            id = 3,
            icon = R.drawable.ic_mobile_password,
            title = R.string.text_change_pin_code,
            actionType = SettingsActionType.Expand
        )
    ),
    PushNotifications(
        SettingsActionItem(
            id = 4,
            icon = R.drawable.ic_bell_notifications,
            title = R.string.text_push_notifications,
            actionType = SettingsActionType.Switch
        )
    ),
    ChangeAppLanguage(
        SettingsActionItem(
            id = 5,
            icon = R.drawable.ic_language_translate,
            title = R.string.text_change_language,
            actionType = SettingsActionType.Expand
        )
    ),
    SubscriptionPlan(
        SettingsActionItem(
            id = 6,
            icon = R.drawable.ic_stars_light_square,
            title = R.string.text_subscription_plan,
            actionType = SettingsActionType.Navigate
        )
    ),
    SetSpendingLimits(
        SettingsActionItem(
            id = 7,
            icon = R.drawable.ic_credit_card,
            title = R.string.text_set_spending_limits,
            actionType = SettingsActionType.Navigate
        )
    ),
    CreateFinancialGoals(
        SettingsActionItem(
            id = 8,
            icon = R.drawable.ic_credit_card_heart_circle,
            title = R.string.text_create_financial_goals,
            actionType = SettingsActionType.Navigate
        )
    ),
    BudgetReminders(
        SettingsActionItem(
            id = 9,
            icon = R.drawable.ic_credit_card_chart,
            title = R.string.text_budget_reminders,
            actionType = SettingsActionType.Navigate
        )
    ),
    HelpCenter(
        SettingsActionItem(
            id = 10,
            icon = R.drawable.ic_chat_bubble,
            title = R.string.text_help_center,
            actionType = SettingsActionType.Navigate
        )
    ),
    Faq(
        SettingsActionItem(
            id = 11,
            icon = R.drawable.ic_faq,
            title = R.string.text_faq,
            actionType = SettingsActionType.Navigate
        )
    )
}

enum class SettingsActionHeader(
    val actions: List<SettingsAction>,
    @StringRes val title: Int
) {
    AccountSettings(
        actions = listOf(
            SettingsAction.EditProfile,
            SettingsAction.ChangePassword,
            SettingsAction.ChangePinCode,
            SettingsAction.PushNotifications,
            SettingsAction.ChangeAppLanguage,
            SettingsAction.SubscriptionPlan
        ),
        title = R.string.text_account_settings
    ),
    BudgetAndGoals(
        actions = listOf(
            SettingsAction.SetSpendingLimits,
            SettingsAction.CreateFinancialGoals,
            SettingsAction.BudgetReminders
        ),
        title = R.string.text_budget_and_goals
    ),
    SupportAndFeedback(
        actions = listOf(
            SettingsAction.HelpCenter,
            SettingsAction.Faq
        ),
        title = R.string.text_support_and_feedback
    )
}