import { BackButton, Button, Header } from '@/components';
import { getUserInfo } from '../_api';
import { format } from 'date-fns';

function InfoRow({ keyName, value }: { keyName: string; value: string }) {
  return (
    <p className="w-full flex justify-between custom-medium-text">
      <span>{keyName}</span>
      <span>{value}</span>
    </p>
  );
}

const koreanKeyName: { [key: string]: string } = {
  name: '이름',
  telephone: '휴대폰 번호',
  email: '이메일',
  createdAt: '가입 일자',
  accountNumber: '계좌 번호',
  address: '주소',
};

export default async function UserInfo() {
  const infoData = await getUserInfo();

  return (
    <div>
      <Header>
        <BackButton label="내 정보 확인" />
      </Header>
      <section className="p-[1.2rem] flex flex-col gap-[1rem]">
        {Object.entries(infoData).map((data, index) => {
          return (
            Object.keys(koreanKeyName).includes(data[0]) && (
              <InfoRow
                key={`info-${index}`}
                keyName={koreanKeyName[data[0]]}
                value={
                  data[0] === 'createdAt'
                    ? format(infoData[data[0]], 'yyyy-MM-dd')
                    : infoData[data[0]]
                }
              />
            )
          );
        })}
      </section>
    </div>
  );
}
