package com.dangsan.dotjoin.modules.toyproject.service;

import com.dangsan.dotjoin.modules.toyproject.dto.memoir.InquireAllMemoir;
import com.dangsan.dotjoin.modules.toyproject.dto.question.InquireAllQuestion;
import com.dangsan.dotjoin.modules.toyproject.dto.toyproject.ToyProjectSimple;
import com.dangsan.dotjoin.modules.toyproject.model.ToyProject;
import com.dangsan.dotjoin.modules.toyproject.repository.ToyProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToyProjectService {

    private final ToyProjectRepository projectRepository;

    public List<ToyProjectSimple> findMyProject() {
        List<ToyProjectSimple> projects = projectRepository.findAllByProjectStartGreaterThanEqualAndProjectEndLessThanEqual(LocalDate.now(), LocalDate.now());

        return projects;
    }

    public List<InquireAllMemoir> findAllMemoirs() {
        List<ToyProject> toyProjects = projectRepository.findAll();
        List<InquireAllMemoir> memoirs = new ArrayList<InquireAllMemoir>();

        toyProjects.forEach(toy -> {
            toy.getSubProjects().forEach(sub -> {
                sub.getProjectMemoirs().forEach(memoir -> {
                    InquireAllMemoir inquireMemoir = new InquireAllMemoir();

                    inquireMemoir.setMemoirId(memoir.getId());
                    inquireMemoir.setProjectName(toy.getTitle());
                    inquireMemoir.setSubProjectName(sub.getTitle());
                    inquireMemoir.setTitle(memoir.getTitle());
                    inquireMemoir.setWhatDid(memoir.getWhatDid());
                    inquireMemoir.setWhatLearn(memoir.getWhatLearn());
                    inquireMemoir.setWhatProblem(memoir.getWhatProblem());
                    inquireMemoir.setHowSolution(memoir.getHowSolution());
                    inquireMemoir.setWhyReason(memoir.getWhyReason());

                    List<String> referenceURLs = new ArrayList<String>();
                    memoir.getReferenceUrl().forEach(reference -> {
                        referenceURLs.add(reference.getUrl());
                    });

                    inquireMemoir.setReferenceUrls(referenceURLs);

                    List<String> gitURLs = new ArrayList<String>();
                    memoir.getGitUrl().forEach(git -> {
                        gitURLs.add(git.getUrl());
                    });

                    inquireMemoir.setGitUrls(gitURLs);

                    memoirs.add(inquireMemoir);
                });
            });
        });

        return memoirs;
    }

    public List<InquireAllQuestion> findAllQuestions() {
        List<ToyProject> toyProjects = projectRepository.findAll();
        List<InquireAllQuestion> questions = new ArrayList<InquireAllQuestion>();

        toyProjects.forEach(toy -> {
            toy.getSubProjects().forEach(sub -> {
                sub.getProjectQuestions().forEach(question -> {
                    InquireAllQuestion inquireQuestion = new InquireAllQuestion();

                    inquireQuestion.setQuestionId(question.getId());
                    inquireQuestion.setQuestioner(question.getQuestioner().getNickname());
                    inquireQuestion.setTitle(question.getTitle());
                    inquireQuestion.setContent(question.getContent());
                    inquireQuestion.setRate(question.getRate());

                    questions.add(inquireQuestion);
                });
            });
        });

        return questions;
    }

    public ToyProjectSimple findTargetProject() {
        ToyProjectSimple targetToyProject = new ToyProjectSimple();
        ToyProject toyProject = projectRepository.findById();

        targetToyProject.setTitle(toyProject.getTitle());
        targetToyProject.setShotDescription(toyProject.getShotDescription());
        targetToyProject.setFullDescription(toyProject.getFullDescription());
        targetToyProject.setImagePath(toyProject.getImagePath());
        targetToyProject.setProjectStart(toyProject.getProjectStart());
        targetToyProject.setProjectEnd(toyProject.getProjectEnd());

        return targetToyProject;
    }
}
