import type { Meta, StoryObj } from '@storybook/react';

import GuideAccordion from './GuideAccordion';

const meta = {
  title: 'Common/GuideAccordion',
  component: GuideAccordion,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
    icon: {
      description: '아이콘 지정',
    },
    title: {
      description: '아코디언을 펼치기 전 표시할 텍스트',
    },
    children: {
      description: '아코디언을 펼친 후 보여질 컴포넌트',
    },
  },
} satisfies Meta<typeof GuideAccordion>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Main: Story = {
  args: {
    icon: 'http://localhost:3000/images/icon-bulb.png',
    title: '요술램프란? 날씨 주식이란?',
    children: (
      <div className="text-custom-white">
        <div className="custom-light-text">
          자스민을 관리하는 두가지 방법이 있어요
        </div>
        <div className="mt-4 grid gap-2">
          <div className="custom-medium-text underline underline-offset-4">
            요술램프(파킹통장)
          </div>
          <div className="custom-light-text whitespace-pre">
            <p>주식에 투자하지 않고 요술램프에 보관할 수도 있어요</p>
            <p>보관된 자스민에는 연 2%의 이자가 생겨요</p>
          </div>
        </div>
        <div className="mt-4 grid gap-2">
          <div className="custom-medium-text underline underline-offset-4">
            날씨 주식에 투자하기
          </div>
          <div className="custom-light-text whitespace-pre-line">
            <p>내 주식 가격은</p>
            <p>첫째, 사람들의 구매와 판매에 의해 실시간으로 결정돼요!</p>
            <p>둘째, 매일 주식 시장이 열릴 때 날씨로 인한 변동이 있어요!</p>
          </div>
        </div>
      </div>
    ),
  },
};
