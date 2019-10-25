package namnh.clean.domain.interactor.usecase

import io.reactivex.Single
import namnh.clean.domain.entity.Repo
import namnh.clean.domain.executor.PostExecutionThread
import namnh.clean.domain.executor.ThreadExecutor
import namnh.clean.domain.interactor.inputport.SingleUseCase
import namnh.clean.domain.repository.RepoRepository

class SearchReposUseCase(
    threadExecutor: ThreadExecutor,
    postExecutionThread: PostExecutionThread,
    private val repoRepository: RepoRepository
) : SingleUseCase<SearchReposUseCase.Input, List<Repo>>(threadExecutor, postExecutionThread) {

    override fun buildUseCase(input: Input): Single<List<Repo>> {
        return repoRepository.search(input.query, input.page)
    }

    class Input(val query: String, val page: Int)
}