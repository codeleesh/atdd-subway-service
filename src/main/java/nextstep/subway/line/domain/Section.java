package nextstep.subway.line.domain;

import nextstep.subway.station.domain.Station;

import javax.persistence.*;

@Entity
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "line_id")
    private Line line;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "up_station_id")
    private Station upStation;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "down_station_id")
    private Station downStation;

    @Embedded
    private Distance distance;

    protected Section() {
    }

    private Section(final Line line, final Station upStation, final Station downStation, final Distance distance) {
        this.line = line;
        this.upStation = upStation;
        this.downStation = downStation;
        this.distance = distance;
    }

    private Section(final Station upStation, final Station downStation, final Distance distance) {
        this(null, upStation, downStation, distance);
    }

    public static Section of(final Line line, final Station upStation, final Station downStation, final Distance distance) {
        return new Section(line, upStation, downStation, distance);
    }

    public static Section of(final Station upStation, final Station downStation, final Distance distance) {
        return new Section(upStation, downStation, distance);
    }

    public Long getId() {
        return id;
    }

    public Line getLine() {
        return line;
    }

    public Station getUpStation() {
        return upStation;
    }

    public Station getDownStation() {
        return downStation;
    }

    public int getDistance() {
        return this.distance.getDistance();
    }

    public void addLine(Line line) {
        this.line = line;
    }

    public boolean isUpStationAndTargetDownStationEquals(Section targetSection) {
        return this.upStation.equals(targetSection.downStation);
    }

    public boolean isDownStationEquals(Section targetSection) {
        return this.downStation.equals(targetSection.downStation);
    }

    public boolean isDownStationAndTargetUpStationEquals(Section targetSection) {
        return this.downStation.equals(targetSection.upStation);
    }

    public boolean isUpStationEquals(Section targetSection) {
        return this.upStation.equals(targetSection.upStation);
    }

    public void changeDownStation(Section targetSection) {
        this.downStation = targetSection.downStation;
    }

    public void changeUpStation(Section targetSection) {
        this.upStation = targetSection.upStation;
    }

    public boolean isDownStationEquals(Station station) {
        return this.downStation.equals(station);
    }

    public boolean isUpStationEquals(Station station) {
        return this.upStation.equals(station);
    }

    public void merge(Section targetSection) {
        this.downStation = targetSection.downStation;
        this.distance = this.distance.plus(targetSection.distance);
    }

    public void minusDistance(Section targetSection) {
        this.distance = this.distance.minus(targetSection.distance);
    }
}
