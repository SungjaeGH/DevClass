// 테라폼 설정의 시작
terraform {
  // 필요한 provider(클라우드 서비스 제공자) 설정
  required_providers {
    // AWS Provider를 사용한다고 선언
    aws = {
      // AWS Provider의 출처를 hashicorp/aws로 지정
      source = "hashicorp/aws"
    }
  }
}

// AWS를 제공자로 사용한다고 선언
provider "aws" {
  // AWS에서 사용할 resion을 변수로부터 받아옴
  region = var.region
}

// AWS VPC(Virtual Private Cloud) 리소스를 생성하고 이름을 'vpc_1'로 설정
resource "aws_vpc" "vpc_1" {
  // VPC의 IP 주소 범위를 설정
  cidr_block = "10.0.0.0/16"

  // DNS 지원을 활성화
  enable_dns_support = true
  // DNS 호스트 이름 지정을 활성화
  enable_dns_hostnames = true

  // 리소스에 대한 태그를 설정
  tags = {
    Name = "${var.prefix}-vpc-1"
  }
}

// AWS 서브넷 리소스를 생성하고 이름을 'subnet_1'로 설정
resource "aws_subnet" "subnet_1" {
  // 이 서브넷이 속할 VPC를 지정. 여기서는 'vpc_1'를 선택
  vpc_id = aws_vpc.vpc_1.id
  // 서브넷의 IP 주소 범위를 설정
  cidr_block = "10.0.1.0/24"
  // 서브넷이 위치할 가용 영역을 설정
  availability_zone = "${var.region}a"
  // 이 서브넷에 배포되는 인스턴스에 공용 IP를 자동으로 할당
  map_public_ip_on_launch = true

  // 리소스에 대한 태그를 설정
  tags = {
    Name = "${var.prefix}-subnet-1"
  }
}

// AWS 서브넷 리소스를 생성하고 이름을 'subnet_2'로 설정
resource "aws_subnet" "subnet_2" {
  // 이 서브넷이 속할 VPC를 지정. 여기서는 'vpc_1'를 선택
  vpc_id = aws_vpc.vpc_1.id
  // 서브넷의 IP 주소 범위를 설정
  cidr_block = "10.0.2.0/24"
  // 서브넷이 위치할 가용 영역을 설정
  availability_zone = "${var.region}b"
  // 이 서브넷에 배포되는 인스턴스에 공용 IP를 자동으로 할당
  map_public_ip_on_launch = true

  // 리소스에 대한 태그를 설정
  tags = {
    Name = "${var.prefix}-subnet-2"
  }
}