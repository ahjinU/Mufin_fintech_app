import { BackButton, Header } from '@/components';
import { getUserInfo } from '../_api';

function InfoRow({ keyName, value }: { keyName: string; value: string }) {
  return (
    <p className="w-full flex justify-between custom-medium-text">
      <span>{keyName}</span>
      <span>{value}</span>
    </p>
  );
}

const dummyData = {
  name: '쟈민',
  telephone: '010-0000-0000',
  email: 'js6666@naver.com',
  createdAt: '2024.03.27',
  accountNumber: '10100-101001',
  address: '경기도 수원시 장안구 천천동 하하하하하로',
  test: '난 출력되면 아니되오.',
};

const koreanKeyName: { [key: string]: string } = {
  name: '이름',
  telephone: '휴대폰 번호',
  email: '이메일',
  createdAt: '가입 일자',
  accountNumber: '계좌 번호',
  address: '주소',
};

export default async function UserInfo() {
  // 여기에 내 정보 조회 fetch 문 놓기,  _api/index.ts에 위치
  // const infoData = await getUserInfo();

  return (
    <>
      <Header>
        <BackButton label="내 정보 확인" />
      </Header>
      <section className="p-[1.2rem] flex flex-col gap-[1rem]">
        {Object.entries(dummyData).map((data, index) => {
          return (
            Object.keys(koreanKeyName).includes(data[0]) && (
              <InfoRow
                key={`info-${index}`}
                keyName={koreanKeyName[data[0]]}
                value={data[1]}
              />
            )
          );
        })}
      </section>
    </>
  );
}
