## Docker Install Script

```shell
#!/bin/bash
# 최종 개선 VM 시작 스크립트: Docker Engine 및 Docker Compose 설치 (Debian 용)
# - 상세 로깅, 체크포인트, 최종 확인, 파일 로깅(/var/log/startup-script.log) 추가

# 로그 파일 경로 정의
LOG_FILE="/var/log/startup-script.log"

# --- 스크립트의 모든 표준 출력과 표준 오류를 로그 파일 및 기존 출력으로 방향 재지정 ---
# 스크립트 시작 시 한 번만 실행
# tee -a: 파일에 내용을 추가 (append)
exec > >(tee -a "$LOG_FILE") 2> >(tee -a "$LOG_FILE" >&2)
# ------------------------------------------------------------------------------------

# 스크립트 실행 중 오류 발생 시 즉시 중단
set -e

LOG_PREFIX=">>>>> [StartupScript-DockerInstall]" # 로그 필터링을 위한 접두사

echo "$LOG_PREFIX =================================================="
echo "$LOG_PREFIX 스크립트 실행 시작 (Timestamp: $(date))"
echo "$LOG_PREFIX 로그 파일: $LOG_FILE"
echo "$LOG_PREFIX 현재 사용자: $(whoami)"
echo "$LOG_PREFIX 초기 메모리 상태:"
free -h || echo "$LOG_PREFIX 'free -h' 명령어 실행 실패 (무시하고 계속)"
echo "$LOG_PREFIX =================================================="

# Docker가 이미 설치되어 있는지 확인
echo "$LOG_PREFIX Docker 설치 여부 확인 중..."
if ! command -v docker &> /dev/null; then
    echo "$LOG_PREFIX Docker 미설치 상태 감지. 설치 프로세스 시작..."

    # --- Checkpoint 1: 패키지 목록 업데이트 ---
    echo "$LOG_PREFIX Checkpoint 1: 시스템 패키지 목록 업데이트 시도 (apt-get update)..."
    sudo apt-get update -y
    echo "$LOG_PREFIX Checkpoint 1: 시스템 패키지 목록 업데이트 완료."

    # --- Checkpoint 2: 필수 패키지 설치 ---
    echo "$LOG_PREFIX Checkpoint 2: 필수 패키지(ca-certificates, curl, gnupg, lsb-release) 설치 시도..."
    sudo apt-get install -y \
        ca-certificates \
        curl \
        gnupg \
        lsb-release
    echo "$LOG_PREFIX Checkpoint 2: 필수 패키지 설치 완료."

    # --- Checkpoint 3: GPG 키 디렉토리 생성 ---
    echo "$LOG_PREFIX Checkpoint 3: Docker GPG 키 저장 디렉토리(/etc/apt/keyrings) 생성 시도..."
    sudo install -m 0755 -d /etc/apt/keyrings
    echo "$LOG_PREFIX Checkpoint 3: GPG 키 저장 디렉토리 생성 완료."

    # --- Checkpoint 4: GPG 키 다운로드 및 저장 ---
    echo "$LOG_PREFIX Checkpoint 4: Docker GPG 키 다운로드 및 저장 시도 (curl | gpg)..."
    curl -fsSL https://download.docker.com/linux/debian/gpg | sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
    echo "$LOG_PREFIX Checkpoint 4: GPG 키 다운로드 및 저장 완료."

    # --- Checkpoint 5: GPG 키 권한 설정 ---
    echo "$LOG_PREFIX Checkpoint 5: GPG 키 파일 권한 설정 시도 (chmod)..."
    sudo chmod a+r /etc/apt/keyrings/docker.gpg
    echo "$LOG_PREFIX Checkpoint 5: GPG 키 파일 권한 설정 완료."

    # --- Checkpoint 6: Docker APT 저장소 설정 ---
    echo "$LOG_PREFIX Checkpoint 6: Docker APT 저장소 설정 시도 (tee)..."
    echo \
      "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] https://download.docker.com/linux/debian \
      $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | \
      sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
    echo "$LOG_PREFIX Checkpoint 6: Docker APT 저장소 설정 완료."

    # --- Checkpoint 7: 패키지 목록 다시 업데이트 ---
    echo "$LOG_PREFIX Checkpoint 7: 새 저장소 반영 위해 패키지 목록 다시 업데이트 시도 (apt-get update)..."
    sudo apt-get update -y
    echo "$LOG_PREFIX Checkpoint 7: 패키지 목록 다시 업데이트 완료."

    # --- Checkpoint 8: Docker 관련 패키지 설치 ---
    echo "$LOG_PREFIX Checkpoint 8: Docker 관련 패키지(docker-ce, ...) 설치 시도 (apt-get install)..."
    # 설치 과정의 자세한 출력을 보기 위해 이 부분만 tee를 쓰지 않을 수도 있지만, 일단은 유지
    sudo apt-get install -y \
        docker-ce \
        docker-ce-cli \
        containerd.io \
        docker-buildx-plugin \
        docker-compose-plugin
    echo "$LOG_PREFIX Checkpoint 8: Docker 관련 패키지 설치 완료."

    echo "$LOG_PREFIX Docker 설치 프로세스 성공적으로 완료."
else
    echo "$LOG_PREFIX Docker 이미 설치되어 있음. 설치 단계 건너뛰기."
fi

# --- Checkpoint 9: Docker 서비스 활성화 및 시작 ---
echo "$LOG_PREFIX Checkpoint 9: Docker 서비스 활성화(enable) 시도 (systemctl enable)..."
sudo systemctl enable docker
echo "$LOG_PREFIX Checkpoint 9: Docker 서비스 활성화 완료."
echo "$LOG_PREFIX Checkpoint 9: Docker 서비스 시작(start) 시도 (systemctl start)..."
sudo systemctl start docker
echo "$LOG_PREFIX Checkpoint 9: Docker 서비스 시작 명령 실행 완료."

# --- 최종 확인 단계 ---
echo "$LOG_PREFIX =================================================="
echo "$LOG_PREFIX 최종 상태 확인 시작..."
echo "$LOG_PREFIX Docker 버전 확인:"
docker --version || echo "$LOG_PREFIX WARN: 'docker --version' 명령어 실행 실패"

echo "$LOG_PREFIX Docker Compose 버전 확인:"
docker compose version || echo "$LOG_PREFIX WARN: 'docker compose version' 명령어 실행 실패"

echo "$LOG_PREFIX Docker 서비스 상태 확인 (systemctl status):"
# status 명령어는 출력이 길 수 있으므로 에러 발생 시에만 로그 남김
sudo systemctl status docker --no-pager || echo "$LOG_PREFIX WARN: 'systemctl status docker' 명령어 실행 실패 (서비스가 아직 완전히 시작되지 않았거나 문제가 있을 수 있음)"

echo "$LOG_PREFIX 최종 메모리 상태:"
free -h || echo "$LOG_PREFIX 'free -h' 명령어 실행 실패 (무시하고 계속)"

echo "$LOG_PREFIX 스크립트 실행 완료 (Timestamp: $(date))"
echo "$LOG_PREFIX =================================================="
```