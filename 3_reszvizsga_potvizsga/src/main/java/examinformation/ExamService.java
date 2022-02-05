package examinformation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class ExamService {
    private Map<String, ExamResult> results = new TreeMap<>();
    private int theoryMax;
    private int practiceMax;

    public Map<String, ExamResult> getResults() {
        return new TreeMap<>(results);
    }

    public int getTheoryMax() {
        return theoryMax;
    }

    public int getPracticeMax() {
        return practiceMax;
    }

    public void setTheoryMax(int theoryMax) {
        this.theoryMax = theoryMax;
    }

    public void setPracticeMax(int practiceMax) {
        this.practiceMax = practiceMax;
    }

    public void readFromFIle(Path path) {
        List<String> fromFile = new ArrayList<>();
        try {
            fromFile = Files.readAllLines(path);
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Cannot read file: " + ioe.getMessage());
        }
        setMaxPoints(fromFile.get(0));
        putToMap(fromFile);
    }

    public void setMaxPoints(String data) {
        String[] maxPoints = data.split(" ");
        setPracticeMax(Integer.parseInt(maxPoints[1]));
        setTheoryMax(Integer.parseInt(maxPoints[0]));
    }

    public void putToMap(List<String> data) {
        for (int i = 1; i < data.size(); i++) {
            String[] tempArr = data.get(i).split(";");
            String[] pointsArr = tempArr[1].split(" ");
            results.put(tempArr[0], new ExamResult(Integer.parseInt(pointsArr[0]), Integer.parseInt(pointsArr[1])));
        }
    }

    public List<String> findPeopleFailed() {
        List<String> names = new ArrayList<>();
        for (Map.Entry<String, ExamResult> entry : results.entrySet()) {
            if (entry.getValue().getTheory() < (theoryMax * 0.51) || entry.getValue().getPractice() < (practiceMax * 0.51)) {
                names.add(entry.getKey());
            }

        }
        return names;
    }

    public String findBestPerson() {
        Set<String> names = new TreeSet<>();
        int best = Integer.MIN_VALUE;
        List<String> failedPpls = findPeopleFailed();

        for (Map.Entry<String, ExamResult> entry : results.entrySet()) {
            if (best < (entry.getValue().getPractice() + entry.getValue().getTheory()) || !failedPpls.contains(entry.getKey())) {
                best = entry.getValue().getPractice() + entry.getValue().getTheory();
            }
        }

        for (Map.Entry<String, ExamResult> entry : results.entrySet()) {
            if (best == (entry.getValue().getPractice() + entry.getValue().getTheory())) {
                names.add(entry.getKey());
            }
        }

        return names.stream().findFirst().get();
    }


}
