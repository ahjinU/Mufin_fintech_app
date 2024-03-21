'use client';

import {
  AdBox,
  ComplexInput,
  FlexBox,
  GuideText,
  NavText,
  OtherInfoElement,
} from '@/components';
import { WeatherType } from '../../page';
import MainRanking from './MainRanking';
import Link from 'next/link';

const WeatherDescript = (id: number) => {
  switch (id / 100) {
    case 2:
      return '천둥이 쳐요';
    case 3:
      return '가랑비가 내려요';
    case 5:
      return '비가 와요';
    case 6:
      return '눈이 와요';
    case 8:
      return '날씨가 맑아요';
  }
};

export default function MainStockList({ data }: { data: WeatherType }) {
  return (
    <div className="p-[1.2rem] flex flex-col gap-[1rem] overflow-y-hidden">
      <AdBox
        icon={`/images/icon-weather.png`}
        mode={'WEATHER'}
        subText={'오늘의 날씨는?'}
        title={`현재 기온은 ${data?.temp}°C로 ${WeatherDescript(
          data?.description,
        )}`}
      />
      <AdBox
        icon="/images/icon-dollar.png"
        mode={'STATIC'}
        subText={'매일 낮 12시, 내 주식에 서울 날씨에 따른 변동이 있어요!'}
        title={'주식은 오후 12시부터 7시까지 열려요'}
      />
      <GuideText
        text={
          <div>
            <span>
              실제 주식 시간은 오전 9시부터 오후 3시이며, <br />
              날씨에 따른 주가 변화는 자스민에만 있는 서비스입니다.
            </span>
          </div>
        }
      />
      <FlexBox
        isDivided={true}
        topChildren={
          <MainRanking
            leftExplainText={'김라딘'}
            leftHighlightText={'실시간 랭킹 1위'}
            rightExplainText={'내 실시간 랭킹은? 427위'}
            rightHighlightText={'999,999,990자스민'}
          />
        }
        bottomChildren={
          <div
            className="custom-medium-text text-custom-dark-gray 
          cursor-pointer flex w-full items-center justify-center h-[0rem] mt-3"
          >
            <Link href={'./'}>
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
            <div className="flex flex-col gap-[1rem]">
              <OtherInfoElement
                imageSrc={'/images/icon-dollar.png'}
                leftExplainText={'오늘 거래량 3,210주'}
                leftHighlightText={'우산회사'}
                state={'DOWN'}
                rightExplainText={'-53.2%'}
                rightHighlightText={'181,000초코칩'}
              />
              <OtherInfoElement
                imageSrc={'/images/icon-dollar.png'}
                leftExplainText={'오늘 거래량 3,210주'}
                leftHighlightText={'우산회사'}
                state={'UP'}
                rightExplainText={'53.2%'}
                rightHighlightText={'181,000초코칩'}
              />
              <OtherInfoElement
                imageSrc={'/images/icon-dollar.png'}
                leftExplainText={'오늘 거래량 3,210주'}
                leftHighlightText={'우산회사'}
                state={'UP'}
                rightExplainText={'53.2%'}
                rightHighlightText={'181,000초코칩'}
              />
            </div>
          }
        />
      </ComplexInput>
    </div>
  );
}
