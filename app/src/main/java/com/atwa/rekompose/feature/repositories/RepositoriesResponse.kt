package com.atwa.rekompose.feature.repositories

import kotlinx.serialization.Serializable


@Serializable
data class RepositoriesResponse(val items: List<RepositoryDto>)

@Serializable
data class RepositoryDto(
    val allow_forking: Boolean? = null,
    val archive_url: String? = null,
    val archived: Boolean? = null,
    val assignees_url: String? = null,
    val blobs_url: String? = null,
    val branches_url: String? = null,
    val clone_url: String? = null,
    val collaborators_url: String? = null,
    val comments_url: String? = null,
    val commits_url: String? = null,
    val compare_url: String? = null,
    val contents_url: String? = null,
    val contributors_url: String? = null,
    val created_at: String? = null,
    val default_branch: String? = null,
    val deployments_url: String? = null,
    val description: String? = null,
    val disabled: Boolean? = null,
    val downloads_url: String? = null,
    val events_url: String? = null,
    val fork: Boolean? = null,
    val forks: Int? = null,
    val forks_count: Int? = null,
    val forks_url: String? = null,
    val full_name: String? = null,
    val git_commits_url: String? = null,
    val git_refs_url: String? = null,
    val git_tags_url: String? = null,
    val git_url: String? = null,
    val has_discussions: Boolean? = null,
    val has_downloads: Boolean? = null,
    val has_issues: Boolean? = null,
    val has_pages: Boolean? = null,
    val has_projects: Boolean? = null,
    val has_wiki: Boolean? = null,
    val homepage: String? = null,
    val hooks_url: String? = null,
    val html_url: String? = null,
    val id: Int,
    val is_template: Boolean? = null,
    val issue_comment_url: String? = null,
    val issue_events_url: String? = null,
    val issues_url: String? = null,
    val keys_url: String? = null,
    val labels_url: String? = null,
    val language: String? = null,
    val languages_url: String? = null,
    val license: License? = null,
    val merges_url: String? = null,
    val milestones_url: String? = null,
    val name: String? = null,
    val node_id: String? = null,
    val notifications_url: String? = null,
    val open_issues: Int? = null,
    val open_issues_count: Int? = null,
    val owner: Owner? = null,
    val private: Boolean? = null,
    val pulls_url: String? = null,
    val pushed_at: String? = null,
    val releases_url: String? = null,
    val score: Double? = null,
    val size: Int? = null,
    val ssh_url: String? = null,
    val stargazers_count: Int? = null,
    val stargazers_url: String? = null,
    val statuses_url: String? = null,
    val subscribers_url: String? = null,
    val subscription_url: String? = null,
    val svn_url: String? = null,
    val tags_url: String? = null,
    val teams_url: String? = null,
    val topics: List<String?>? = null,
    val trees_url: String? = null,
    val updated_at: String? = null,
    val url: String? = null,
    val visibility: String? = null,
    val watchers: Int? = null,
    val watchers_count: Int? = null,
    val web_commit_signoff_required: Boolean? = null
)  {
    @Serializable
    data class License(
        val key: String?,
        val name: String?,
        val node_id: String?,
        val spdx_id: String?,
        val url: String?
    )
    @Serializable
    data class Owner(
        val avatar_url: String?,
        val events_url: String?,
        val followers_url: String?,
        val following_url: String?,
        val gists_url: String?,
        val gravatar_id: String?,
        val html_url: String?,
        val id: Int?,
        val login: String?,
        val node_id: String?,
        val organizations_url: String?,
        val received_events_url: String?,
        val repos_url: String?,
        val site_admin: Boolean?,
        val starred_url: String?,
        val subscriptions_url: String?,
        val type: String?,
        val url: String?
    )
}

fun RepositoryDto.toDomain() : Repository {
    return Repository(
        id = id,
        ownerName = owner?.login ?: "",
        ownerImageUrl = owner?.avatar_url,
        name = name ?: "",
        description = description,
        language = language,
        stars = stargazers_count
    )
}