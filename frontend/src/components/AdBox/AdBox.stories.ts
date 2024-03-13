import type { Meta, StoryObj } from '@storybook/react';

import AdBox from './AdBox';

const meta = {
  title: 'Common/AdBox',
  component: AdBox,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
    mode: {
      description: 'INTERACTIVE는 영역 클릭 시 페이지를 이동합니다.',
    },
    subText: {
      description: '작은 텍스트',
    },
    title: {
      description: '큰 텍스트',
    },
    icon: {
      description: '(optional) 표시할 아이콘 경로',
    },
    link: {
      description: '(optional) 이동할 페이지 라우트',
    },
  },
} satisfies Meta<typeof AdBox>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Interactive: Story = {
  args: {
    mode: 'INTERACTIVE',
    subText: '영수증을 등록하고 요술램프 혜택을 받아보세요!',
    title: '내 가계부에 세부내역 추가하기',
    icon: 'http://localhost:3000/images/icon-dollar.png',
    link: '/',
  },
};

export const Static: Story = {
  args: {
    mode: 'STATIC',
    subText: '자스민은 아래 두 가지 방법으로 관리할 수 있어요!',
    title: '요술램프 또는 주식 투자로 관리해 보세요',
    icon: 'http://localhost:3000/images/icon-calendar.png',
  },
};

export const StaticNoIcon: Story = {
  args: {
    mode: 'STATIC',
    subText: '상세 정보 추가 또는 더치페이 요청을 할 수 있어요!',
    title: '각 지출 내역을 누르면 선택할 수 있어요',
  },
};
