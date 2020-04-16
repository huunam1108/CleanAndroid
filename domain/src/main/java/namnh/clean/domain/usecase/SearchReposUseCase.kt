package namnh.clean.domain.usecase

import namnh.clean.domain.entity.Repo
import namnh.clean.domain.interactor.inputport.BaseInput
import namnh.clean.domain.repository.RepoRepository
import namnh.clean.domain.usecase.base.BaseUseCase

class SearchReposUseCase(
    private val repoRepository: RepoRepository
) : BaseUseCase<SearchReposUseCase.Input, List<Repo>>() {
    override suspend fun buildUseCase(input: Input): List<Repo> {
        return repoRepository.search(query = input.query, page = input.page)
    }

    data class Input(val query: String, val page: Int) : BaseInput()
}
