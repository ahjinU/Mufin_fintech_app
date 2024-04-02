'use client';

import {
  AdBox,
  ComplexInput,
  FlexBox,
  GuideText,
  OtherInfoElement,
} from '@/components';
import { DataType } from '../page';
import { commaNum } from '@/utils/commaNum';
import MainRanking from './MainRanking';
import Link from 'next/link';
import { useRouter } from 'next/navigation';
import { toCompanyEnglishName } from '../../company/_utils';

const toWeatherDescript = (id: number) => {
  switch (Math.floor(id / 100)) {
    case 2:
      return '천둥이 쳐요.';
    case 3:
      return '가랑비가 내려요.';
    case 5:
      return '비가 와요.';
    case 6:
      return '눈이 와요.';
    case 7:
      return '먼지가 많아요.';
    case 8:
      return '날씨가 맑아요.';
  }
};

export default function MainStockList({ data }: { data: DataType }) {
  const { temp, description, stocks, ranks, myRank } = data;
  const router = useRouter();

  return (
    <div className="p-[1.2rem] flex flex-col gap-[1rem] overflow-y-hidden">
      <AdBox
        icon={`/images/icon-weather.png`}
        mode={'WEATHER'}
        subText={'오늘의 날씨는?'}
        title={`현재 기온은 ${temp}°C로 ${toWeatherDescript(description)}`}
      />
      <AdBox
        icon="/images/icon-dollar.png"
        mode={'STATIC'}
        subText={'매일 아침 9시, 내 주식은 서울 날씨에 따라 변화해요!'}
        title={'시장은 오전 9시부터 오후 4시까지에요.'}
      />
      <GuideText
        text={
          <div>
            <span>
              실제 주식 시간은 오전 9시부터 오후 3시 30분이고
              <br />
              날씨에 따른 주가 변화는 머핀에만 있는 서비스입니다.
            </span>
          </div>
        }
      />
      <FlexBox
        isDivided={true}
        topChildren={
          <MainRanking
            leftExplainText={`${ranks[0]?.childName}`}
            leftHighlightText={'실시간 랭킹 1위'}
            rightExplainText={`내 실시간 랭킹은? ${myRank?.rank}위`}
            rightHighlightText={`${commaNum(myRank?.balance)} 초코칩`}
          />
        }
        bottomChildren={
          <div
            className="custom-medium-text text-custom-dark-gray 
          cursor-pointer flex w-full items-center justify-center h-[0rem] mt-3"
          >
            <Link href={'/stock/rank'}>
              {'랭킹 확인하러 가기 '}
              <span className="text-custom-med-gray">&gt;</span>
            </Link>
          </div>
        }
      />
      <ComplexInput label={'주식 종목 리스트'} mode={'NONE'}>
        <FlexBox
          isDivided={false}
          topChildren={
            <div className="flex flex-col gap-[2rem]">
              {stocks?.map(
                ({ name, price, transCnt, incomeRatio, imageUrl }, index) => {
                  return (
                    <OtherInfoElement
                      key={`stocks-${index}`}
                      imageSrc={imageUrl}
                      leftExplainText={`오늘 거래량 ${commaNum(transCnt)}주`}
                      leftHighlightText={name}
                      state={`${incomeRatio < 0 ? 'DOWN' : 'UP'}`}
                      rightExplainText={`오전 9시보다 ${commaNum(
                        incomeRatio,
                      )}%`}
                      rightHighlightText={`${commaNum(price)} 초코칩`}
                      handleClick={() =>
                        router.push(`/company/${toCompanyEnglishName(name)}`)
                      }
                    />
                  );
                },
              )}
            </div>
          }
        />
      </ComplexInput>
    </div>
  );
}
