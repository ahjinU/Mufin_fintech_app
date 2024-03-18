import type { Meta, StoryObj } from '@storybook/react';

import FlexBox from './FlexBox';
import OtherInfoElement from '../OtherInfoElement/OtherInfoElement';

const meta = {
  title: 'Common/FlexBox',
  component: FlexBox,
  parameters: {},
  tags: ['autodocs'],
  argTypes: {
    isDivided: {
      description: '구분선이 있는지 boolean 값을 prop으로 전달합니다.',
    },
  },
} satisfies Meta<typeof FlexBox>;

export default meta;
type Story = StoryObj<typeof meta>;

export const Divided: Story = {
  args: {
    isDivided: true,
    topChildren: <OtherInfoElement imageSrc='http://localhost:3000/images/icon-dollar.png' leftExplainText='화장품' leftHighlightText='씨제이올리브영 주식회사' state='DOWN' money='50,000 원'/>,
    bottomChildren: <span>5천원 할인 받았다!</span>
  },
};
